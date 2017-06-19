'use strict';

var APP_NAME_${submodule.getUpVal()}= '${submodule}UIApp';
angular.module(APP_NAME_${submodule.getUpVal()} + '.${submodule}', [
    APP_NAME_${submodule.getUpVal()} + '.${submodule}.${objectname.getFirstlowVal()}'
]);
