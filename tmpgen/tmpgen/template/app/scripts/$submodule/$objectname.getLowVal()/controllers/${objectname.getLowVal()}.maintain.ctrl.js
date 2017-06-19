'use strict';
#set ($dolar="$")

angular.module(APP_NAME_${submodule.getUpVal()} + '.${objectname.getFirstlowVal()}.controllers').controller('${objectname.getFirstlowVal()}.maintain.ctrl', ['$scope', '$state', '$stateParams', '$timeout', '$validator', '${objectname.getFirstlowVal()}Service', 'icCommonToastr', 'icCommonBootbox',
    function ($scope, $state, $stateParams, $timeout, $validator, ${objectname.getFirstlowVal()}Service, icCommonToastr, icCommonBootbox) {
        $scope.${objectname.getFirstlowVal()} = {};

        $scope.original${objectname} = angular.copy($scope.${objectname.getFirstlowVal()});
        function initData() {
            if ($stateParams.id) {
                ${objectname.getFirstlowVal()}Service.find($stateParams.id).then(function (data) {
                    $scope.${objectname.getFirstlowVal()} = data;
                    $scope.original${objectname} = angular.copy($scope.${objectname.getFirstlowVal()});
                });
            }
        }

        initData();

        $scope.actions = {
            submit: function () {
                ${dolar}validator.validate(${dolar}scope, '${objectname.getFirstlowVal()}')
                    .success(function () {
                        var promise = '';
                        if ($scope.${objectname.getFirstlowVal()}.id) {
                            promise = ${objectname.getFirstlowVal()}Service.update($scope.${objectname.getFirstlowVal()});
                        } else {
                            promise = ${objectname.getFirstlowVal()}Service.create($scope.${objectname.getFirstlowVal()});
                        }

                        promise.then(
                            function (data) {
                                saveSuccess();
                            }, function (data) {
                                saveFailed(data);
                            }
                        );
                    });
            },
            reset: function () {
                $scope.${objectname.getFirstlowVal()} = $scope.original${objectname};
            },
            cancel: function () {
                goSource();
            }
        };
        function saveSuccess() {
            if ($scope.${objectname.getFirstlowVal()}.id) {
                icCommonToastr.success_updated();
            } else {
                icCommonToastr.success_saved();
            }
            goSource();
        }

        function saveFailed(data) {
            var msg = data && data.message ? APP_NAME_${submodule.getUpVal()} + '.' + data.message : "";
            if ($scope.${objectname.getFirstlowVal()}.id) {
                icCommonToastr.error_updated(msg);
            } else {
                icCommonToastr.error_saved(msg);
            }
            //$scope.actions.reset();
        }

        function goSource() {
            ${dolar}state.go('layout.${objectname.getFirstlowVal()}-list');
        }
    }
]);