package cn.com.nanfeng.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liutao
 * @date 2020-08-28 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fsource {

    private String symbol;

    private String price;

    private String tax;

    private Long rowtime;

}
