package cn.wolfcode.wms.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Base {

    private String name;

    private String sn;

    private List<Permission> permissions;
    private List<SystemMenu> menus;

}