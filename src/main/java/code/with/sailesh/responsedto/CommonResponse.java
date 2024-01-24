package code.with.sailesh.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse {

	@Builder.Default
	private Integer statusCode=200;
	@Builder.Default
	private String msg="SUCCESS";
	private Object data;
}
