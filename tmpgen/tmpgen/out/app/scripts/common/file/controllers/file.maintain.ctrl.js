'use strict';

angular.module(APP_NAME_COMMON + '.file.controllers').controller('file.maintain.ctrl', ['$scope', '$state', '$stateParams', '$timeout', '$validator', 'fileService', 'icCommonToastr', 'icCommonBootbox',
    function ($scope, $state, $stateParams, $timeout, $validator, fileService, icCommonToastr, icCommonBootbox) {
        $scope.file = {};

        $scope.originalFile = angular.copy($scope.file);
        function initData() {
            if ($stateParams.id) {
                fileService.find($stateParams.id).then(function (data) {
                    $scope.file = data;
                    $scope.originalFile = angular.copy($scope.file);
                });
            }
        }

        initData();

        $scope.actions = {
            submit: function () {
                $validator.validate($scope, 'file')
                    .success(function () {
                        var promise = '';
                        if ($scope.file.id) {
                            promise = fileService.update($scope.file);
                        } else {
                            promise = fileService.create($scope.file);
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
                $scope.file = $scope.originalFile;
            },
            cancel: function () {
                goSource();
            }
        };
        function saveSuccess() {
            if ($scope.file.id) {
                icCommonToastr.success_updated();
            } else {
                icCommonToastr.success_saved();
            }
            goSource();
        }

        function saveFailed(data) {
            var msg = data && data.message ? APP_NAME_COMMON + '.' + data.message : "";
            if ($scope.file.id) {
                icCommonToastr.error_updated(msg);
            } else {
                icCommonToastr.error_saved(msg);
            }
            //$scope.actions.reset();
        }

        function goSource() {
            $state.go('layout.file-list');
        }
    }
]);