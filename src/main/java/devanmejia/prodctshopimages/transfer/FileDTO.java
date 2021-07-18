package devanmejia.prodctshopimages.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private String name;
    private byte[] content;
}
