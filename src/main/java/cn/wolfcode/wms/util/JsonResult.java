package cn.wolfcode.wms.util;

import lombok.Getter;

@Getter
public class JsonResult {
    private String msg;
    private boolean success = true;

    public void mark(String msg) {
        success = false;
        this.msg = msg;
    }
}
