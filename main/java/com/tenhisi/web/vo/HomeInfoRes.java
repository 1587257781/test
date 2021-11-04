package com.tenhisi.web.vo;

import com.tenhisi.framework.sys.entity.SysCompEntity;
import lombok.Data;

@Data
public class HomeInfoRes {

    /** 主键 */
    private Long id;

    /** 社区 */
    private Long compId;

    private SysCompEntity company;

    /** 楼栋号 */
    private String houseCode;

    /** 单元号 */
    private String unitCode;

    /** 房间号 */
    private String homeCode;

    private String flags;
}
