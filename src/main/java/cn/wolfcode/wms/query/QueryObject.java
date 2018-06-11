package cn.wolfcode.wms.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject {
    private Integer currentPage = 1;
    private Integer pageSize = 3;

    public int getStartIndex() {
        return pageSize * (currentPage - 1);
    }
}
