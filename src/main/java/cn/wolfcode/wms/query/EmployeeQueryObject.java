package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long dept_id;
}
