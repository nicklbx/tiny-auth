package com.victor.auth.controller.v1;

import com.victor.auth.common.constraints.Update;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.dao.entity.Resource;
import com.victor.auth.model.dto.ResourceDTO;
import com.victor.auth.model.vo.ResourceVo;
import com.victor.auth.service.ResourceService;
import com.victor.auth.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Api(tags = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @javax.annotation.Resource
    private ResourceService resourceService;

    /**
     * 查看所有资源
     *
     * @return
     */
    @ApiOperation(value = "查看所有资源")
    @GetMapping("/listAllResource")
    public ApiRespVo listResource() {
        List<Resource> list = resourceService.list();
        List<ResourceVo> resourceVoList = BeanCopyUtil.copyListProperties(list, ResourceVo::new);
        return R.ok(resourceVoList);
    }

    /**
     * 添加资源
     *
     * @param resourceDTO
     * @return
     */
    @ApiOperation("添加资源")
    @PostMapping("/add")
    public ApiRespVo createResource(@Valid @RequestBody ResourceDTO resourceDTO) {
        Resource resource = new Resource();
        BeanCopyUtil.copyProperties(resourceDTO, resource);
        resource.setResourceId(null);
        resourceService.save(resource);
        return R.ok(resource);
    }


    /**
     * 修改资源
     *
     * @param resourceDTO
     * @return
     */
    @ApiOperation("修改资源")
    @PostMapping("/update")
    public ApiRespVo updateResource(@Validated(Update.class) @RequestBody ResourceDTO resourceDTO) {
        Resource resource = new Resource();
        BeanCopyUtil.copyProperties(resourceDTO, resource);
        resourceService.updateById(resource);
        return R.ok(resource);
    }

    /**
     * 删除资源
     *
     * @param resourceId
     * @return
     */
    @ApiOperation("删除资源")
    @PostMapping("/delete/{resourceId}")
    public ApiRespVo deleteResource(@NotNull @PathVariable Long resourceId) {
        resourceService.removeById(resourceId);
        return R.ok();
    }
}
