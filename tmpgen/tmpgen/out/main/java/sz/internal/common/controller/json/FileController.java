package sz.internal.common.controller.json;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sz.internal.common.base.controller.BaseController;
import sz.internal.common.base.to.PageTO;
import sz.internal.common.service.FileService;
import sz.internal.common.to.FileSearchTO;
import sz.internal.common.to.FileTO;

import javax.annotation.Resource;

@RestController
@RequestMapping("/json/file")
public class FileController extends BaseController {
    @Resource
    private FileService fileService;

    @RequestMapping(value = "/searchFile")
    public Object searchFile(@RequestBody FileTO fileTO) throws Exception {
        return fileService.search(fileTO);
    }

    @RequestMapping("/searchFilePage")
    public Object searchFilePage(@RequestBody FileSearchTO searchTO) throws Exception {
        PageTO pageTO = searchTO.getPageTO();
        FileTO fileTO = new FileTO();
//todo
        fileTO.setSortBy(searchTO.getSortBy());
        fileTO.setSortType(searchTO.getSortType());
        pageTO = fileService.searchPage(fileTO, pageTO);
        return pageTO;
    }

    @RequestMapping("/findFile/{id}")
    public Object findFile(@PathVariable String id) throws Exception {
        int uID = Integer.parseInt(id);
        FileTO fileTO = new FileTO();
        fileTO.setId(uID);
        fileTO = (FileTO) fileService.find(fileTO);
        return fileTO;
    }

    @RequestMapping(value = "/createFile")
    public Object createFile(@RequestBody FileTO fileTO) throws Exception {
        fileService.insert(fileTO);

        return fileTO;
    }

    @RequestMapping(value = "/updateFile")
    public Object updateFile(@RequestBody FileTO fileTO) throws Exception {
        fileService.update(fileTO);

        return fileTO;
    }

    @RequestMapping("/deleteFile/{id}")
    public Object deleteFile(@PathVariable String id) throws Exception {
        int uID = Integer.parseInt(id);
        FileTO fileTO = new FileTO();
        fileTO.setId(uID);

        fileService.delete(fileTO);

        return "";
    }


}
