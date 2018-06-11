package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class OrderBill extends Base {

    public static final int STATUS_NOMAL = 0;//待审核
    public static final int STATUS_AUDIT = 1;//已审核
    private String sn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;

    private int status = STATUS_NOMAL;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    private Employee inputUser;

    private Employee auditor;

    private Supplier supplier;
    //单据详细信息,一对多关系
    private List<OrderBillItem> items = new ArrayList<>();

}