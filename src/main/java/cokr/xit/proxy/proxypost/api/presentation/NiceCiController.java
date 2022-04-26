package cokr.xit.proxy.proxypost.api.presentation;

import cokr.xit.proxy.proxypost.api.support.Interop;
import cokr.xit.proxy.proxypost.core.model.ResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NiceCiController {


    @PostMapping("/nice/ci")
    public ResponseEntity<ResponseVO> getCi(@RequestParam Map<String, String> mParam) {
        String siteCode = mParam.get("site_code");
        String sitePw = mParam.get("site_pw");
        String jid = mParam.get("jid");

        ResponseVO responseVO = Interop.getCI(siteCode, sitePw, jid);

        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

}
