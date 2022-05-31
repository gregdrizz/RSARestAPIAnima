package homeassignment.animabiotech.rsarestapi.controller;

import homeassignment.animabiotech.rsarestapi.model.RSAService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/rsa-instance")
public class RSAApiController {

    private final RSAService rsaService;

    public RSAApiController(RSAService rsaService) {
        this.rsaService = rsaService;
    }

    @GetMapping("/")
    public Set<String> findAll() {
        return rsaService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable String id) {
        rsaService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public String create() throws NoSuchAlgorithmException {
        return rsaService.create();
    }

    /*
    BODY:
        add a string to sign
     */
    @PostMapping("/sign/{id}")
    public String sign(@PathVariable String id,
                       @RequestBody String data) throws Exception {
        return rsaService.sign(data, id);
    }

    /*
    BODY:
        JSON
        {
            "signature": "your_signature_here",
            "data":"your_data_here"
        }
     */
    @PostMapping("/verify/{id}")
    public boolean verify(@PathVariable String id,
                          @RequestBody Map<String, String> json) throws Exception {
        String signature = json.get("signature");
        String data = json.get("data");
        return rsaService.verify(data, signature, id);
    }
}
