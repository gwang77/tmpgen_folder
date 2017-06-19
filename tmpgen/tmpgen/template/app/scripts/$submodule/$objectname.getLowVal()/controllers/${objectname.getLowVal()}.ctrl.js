'use strict';

angular.module(APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}.controllers').controller('${objectname.getFirstlowVal()}.ctrl', ['$scope', 'paginator', 'sorter', 'ctrlStateMgr', '${objectname.getFirstlowVal()}Service', 'icCommonToastr', 'icCommonBootbox',
    function ($scope, paginator, sorter, ctrlStateMgr, ${objectname.getFirstlowVal()}Service, icCommonToastr, icCommonBootbox) {
        $scope.${objectname.getFirstlowVal()}List = [];
        $scope.${objectname.getFirstlowVal()}TO = {
        };

        $scope.searchCriteria = ctrlStateMgr.get('searchCriteria', $scope.${objectname.getFirstlowVal()}TO);
        $scope.pagination = ctrlStateMgr.get('pagination', paginator.create());
        $scope.sorter = ctrlStateMgr.get('sorter', sorter.create());

        $scope.onPageChanged = function () {
            loadData();
        };
        $scope.onSortChanged = function () {
            loadData();
        };
        $scope.onSizeChanged = function () {
            loadData();
        };

        $scope.actions = {
            search: function () {
                loadData();
            },
            delete: function (id) {
                icCommonBootbox.confirm_delete(function (result) {
                    if (result) {
                        ${objectname.getFirstlowVal()}Service.delete(id).then(
                            function (data) {
                                //toastr.success('Customer deleted successfully!', 'Successfully');
                                icCommonToastr.success_deleted();
                                loadData();
                            },
                            function (data) {
                                //toastr.error('Customer deleted failed!', 'Failed');
                                icCommonToastr.error_deleted();
                            }
                        );
                    }
                });
            }
        };

        var loadData = function () {
            var params = _.extend({}, $scope.searchCriteria);

            _.extend(params, $scope.pagination.getPagerParams());
            _.extend(params, $scope.sorter.getSorterParams());

            ctrlStateMgr.set('pagination', $scope.pagination);
            ctrlStateMgr.set('searchCriteria', $scope.searchCriteria);
            ctrlStateMgr.set('sorter', $scope.sorter);

            ${objectname.getFirstlowVal()}Service.searchPage(params).then(function (data) {
                $scope.${objectname.getFirstlowVal()}List = data.list;
                $scope.pagination.count = data.totalRecords;
            });
        };

        loadData();

    }
]);