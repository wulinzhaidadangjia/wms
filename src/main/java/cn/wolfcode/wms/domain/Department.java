package cn.wolfcode.wms.domain;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import lombok.*;

@Getter
@Setter
@ToString
public class Department extends Base {

    private String name;

    private String sn;


}