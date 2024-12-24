package wrq.rotation.common.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String msg="请求成功";
    private int status=200;
    private Object dataList=null;

    public ResponseDTO(Object dataList) {
        this.dataList = dataList;
    }
}
