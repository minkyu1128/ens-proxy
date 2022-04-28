package cokr.xit.proxy.post.api.presentation;

import cokr.xit.proxy.post.api.support.Interop;
import cokr.xit.proxy.post.core.model.ResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NiceCiController {


    @PostMapping("/nice/ci")
    public ResponseEntity<ResponseVO> getCi(@RequestBody Map<String, String> mParam) {
        String siteCode = mParam.get("site_code");
        String sitePw = mParam.get("site_pw");
        String jid = mParam.get("jid");

        ResponseVO responseVO = null;
        if(siteCode == null) responseVO = ResponseVO.errRsltBuilder().errCode("ERR410").errMsg("사이트코드는 필수 조건 입니다.").build();
        else if(sitePw == null) responseVO = ResponseVO.errRsltBuilder().errCode("ERR410").errMsg("사이트패스워드는 필수 조건 입니다.").build();
        else if(jid == null) responseVO = ResponseVO.errRsltBuilder().errCode("ERR410").errMsg("주민번호는 필수 조건 입니다.").build();
        else responseVO = Interop.getCI(siteCode, sitePw, jid);

        log.info("==================================================================================");
        log.info("==== nice CI response.... ====");
        log.info(responseVO.toString());
        log.info("==================================================================================");

        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

}
