'use strict';

var ${objectname.getLowVal()}_module = angular.module(APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}', [
    APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}.controllers',
    APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}.filters',
    APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}.services'
]);

/**
 * customer module configuration.
 * a. ui-router
 */
${objectname.getLowVal()}_module.config(['$stateProvider',
    function ($stateProvider) {

        // ui-router state
        $stateProvider
            .state('layout.${objectname.getFirstlowVal()}-list', {
                templateUrl: 'views/${submodule}/${objectname.getLowVal()}/${objectname.getFirstlowVal()}.list.html',
                url: '/${objectname.getFirstlowVal()}/list',
                controller: '${objectname.getFirstlowVal()}.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_${submodule.getUpVal()} + '.label_maintain_${objectname.getFirstlowVal()}'
                }
            })
            .state('layout.${objectname.getFirstlowVal()}-create', {
                templateUrl: 'views/${submodule}/${objectname.getLowVal()}/${objectname.getFirstlowVal()}.maintain.html',
                url: '/${objectname.getFirstlowVal()}/create',
                controller: '${objectname.getFirstlowVal()}.maintain.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_${submodule.getUpVal()} + '.label_create_${objectname.getFirstlowVal()}',
                    parent: 'layout.${objectname.getFirstlowVal()}-list'
                }
            })
            .state('layout.${objectname.getFirstlowVal()}-edit', {
                templateUrl: 'views/${submodule}/${objectname.getLowVal()}/${objectname.getFirstlowVal()}.maintain.html',
                url: '/${objectname.getFirstlowVal()}/edit/:id',
                controller: '${objectname.getFirstlowVal()}.maintain.ctrl',
                ncyBreadcrumb: {
                    label: APP_NAME_${submodule.getUpVal()} + '.label_edit_${objectname.getFirstlowVal()}',
                    parent: 'layout.${objectname.getFirstlowVal()}-list'
                }
            })
        ;
    }
]);