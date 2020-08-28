package cn.com.nanfeng.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-13 20:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {

    private String id;

    private String code;

    private String description;

    private String url;

}
