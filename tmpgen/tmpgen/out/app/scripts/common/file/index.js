'use strict';

var file_module = angular.module(APP_NAME_COMMON + '.common.file', [
    APP_NAME_COMMON + '.common.file.controllers',
    APP_NAME_COMMON + '.common.file.filters',
    APP_NAME_COMMON + '.common.file.services'
]);

/**
 * customer module configuration.
 * a. ui-router
 */
file_module.config(['$stateProvider',
    function ($stateProvider) {

        // ui-router state
        $stateProvider
            .state('layout.file-list', {
                templateUrl: 'views/common/file/file.list.html',
                url: '/file/list',
                controller: 'file.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_COMMON + '.label_maintain_file'
                }
            })
            .state('layout.file-create', {
                templateUrl: 'views/common/file/file.maintain.html',
                url: '/file/create',
                controller: 'file.maintain.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_COMMON + '.label_create_file',
                    parent: 'layout.file-list'
                }
            })
            .state('layout.file-edit', {
                templateUrl: 'views/common/file/file.maintain.html',
                url: '/file/edit/:id',
                controller: 'file.maintain.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_COMMON + '.label_edit_file',
                    parent: 'layout.file-list'
                }
            })
        ;
    }
]);