package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Supplier extends Base {

    private String name;

    private String phone;

    private String address;

}