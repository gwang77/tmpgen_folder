'use strict';

angular.module(APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}.services').service('${objectname.getFirstlowVal()}Service', ['config', 'icCommonReq',
    function (config, icCommonReq) {
        this.list = function (params) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.list, "POST", null, params);
        };

        this.searchPage = function (params) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.searchPage, "POST", null, params);
        };

        this.find = function (id) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.find + id, "GET");
        };

        this.create = function (params) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.create, "POST", null, params);
        };

        this.update = function (params) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.update, "POST", null, params);
        };

        this.delete = function (id) {
            return icCommonReq.request_sync(config.restful.${submodule}.${objectname.getFirstlowVal()}.delete + id, "POST");
        };

	}
]);