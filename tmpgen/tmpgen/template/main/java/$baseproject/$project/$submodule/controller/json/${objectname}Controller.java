package ${baseproject}.${project}.${submodule}.controller.json;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${baseproject}.${project}.common.base.controller.BaseController;
import ${baseproject}.${project}.common.base.to.PageTO;
import ${baseproject}.${project}.${submodule}.service.${objectname}Service;
import ${baseproject}.${project}.${submodule}.to.${objectname}SearchTO;
import ${baseproject}.${project}.${submodule}.to.${objectname}TO;

import javax.annotation.Resource;

@RestController
@RequestMapping("/json/${objectname.getFirstlowVal()}")
public class ${objectname}Controller extends BaseController {
    @Resource
    private ${objectname}Service ${objectname.getFirstlowVal()}Service;

    @RequestMapping(value = "/search${objectname}")
    public Object search${objectname}(@RequestBody ${objectname}TO ${objectname.getFirstlowVal()}TO) throws Exception {
        return ${objectname.getFirstlowVal()}Service.search(${objectname.getFirstlowVal()}TO);
    }

    @RequestMapping("/search${objectname}Page")
    public Object search${objectname}Page(@RequestBody ${objectname}SearchTO searchTO) throws Exception {
        PageTO pageTO = searchTO.getPageTO();
        ${objectname}TO ${objectname.getFirstlowVal()}TO = new ${objectname}TO();
//todo
        ${objectname.getFirstlowVal()}TO.setSortBy(searchTO.getSortBy());
        ${objectname.getFirstlowVal()}TO.setSortType(searchTO.getSortType());
        pageTO = ${objectname.getFirstlowVal()}Service.searchPage(${objectname.getFirstlowVal()}TO, pageTO);
        return pageTO;
    }

    @RequestMapping("/find${objectname}/{id}")
    public Object find${objectname}(@PathVariable String id) throws Exception {
        int uID = Integer.parseInt(id);
        ${objectname}TO ${objectname.getFirstlowVal()}TO = new ${objectname}TO();
        ${objectname.getFirstlowVal()}TO.setId(uID);
        ${objectname.getFirstlowVal()}TO = (${objectname}TO) ${objectname.getFirstlowVal()}Service.find(${objectname.getFirstlowVal()}TO);
        return ${objectname.getFirstlowVal()}TO;
    }

    @RequestMapping(value = "/create${objectname}")
    public Object create${objectname}(@RequestBody ${objectname}TO ${objectname.getFirstlowVal()}TO) throws Exception {
        ${objectname.getFirstlowVal()}Service.insert(${objectname.getFirstlowVal()}TO);

        return ${objectname.getFirstlowVal()}TO;
    }

    @RequestMapping(value = "/update${objectname}")
    public Object update${objectname}(@RequestBody ${objectname}TO ${objectname.getFirstlowVal()}TO) throws Exception {
        ${objectname.getFirstlowVal()}Service.update(${objectname.getFirstlowVal()}TO);

        return ${objectname.getFirstlowVal()}TO;
    }

    @RequestMapping("/delete${objectname}/{id}")
    public Object delete${objectname}(@PathVariable String id) throws Exception {
        int uID = Integer.parseInt(id);
        ${objectname}TO ${objectname.getFirstlowVal()}TO = new ${objectname}TO();
        ${objectname.getFirstlowVal()}TO.setId(uID);

        ${objectname.getFirstlowVal()}Service.delete(${objectname.getFirstlowVal()}TO);

        return "";
    }


}
