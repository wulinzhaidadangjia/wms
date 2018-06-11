package cn.wolfcode.wms.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Integer currentPage;
    private Integer pageSize;
    private Integer prevPage;
    private Integer nextPage;
    private Integer totalPage;
    private Integer totalCount;
    private List<?> list;

    public static final PageResult EMPTY_RESULT = new PageResult(1, 1, 0, Collections.EMPTY_LIST);

    public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;
        if (totalCount < pageSize) {
            currentPage = 1;
            totalPage = 1;
            nextPage = 1;
            return;
        }
        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
    }



}
