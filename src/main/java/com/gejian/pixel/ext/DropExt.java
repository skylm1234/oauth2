package com.gejian.pixel.ext;

import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.model.DropItem;
import lombok.Data;

import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Data
public class DropExt extends Drop {

	private List<List<DropItem>> contents;

}
