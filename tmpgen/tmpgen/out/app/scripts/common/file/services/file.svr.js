'use strict';

angular.module(APP_NAME_COMMON + '.common.file.services').service('fileService', ['config', 'icCommonReq',
    function (config, icCommonReq) {
        this.list = function (params) {
            return icCommonReq.request_sync(config.restful.common.file.list, "POST", null, params);
        };

        this.searchPage = function (params) {
            return icCommonReq.request_sync(config.restful.common.file.searchPage, "POST", null, params);
        };

        this.find = function (id) {
            return icCommonReq.request_sync(config.restful.common.file.find + id, "GET");
        };

        this.create = function (params) {
            return icCommonReq.request_sync(config.restful.common.file.create, "POST", null, params);
        };

        this.update = function (params) {
            return icCommonReq.request_sync(config.restful.common.file.update, "POST", null, params);
        };

        this.delete = function (id) {
            return icCommonReq.request_sync(config.restful.common.file.delete + id, "POST");
        };

	}
]);