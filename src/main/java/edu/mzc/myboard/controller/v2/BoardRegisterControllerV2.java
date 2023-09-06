package edu.mzc.myboard.controller.v2;

import edu.mzc.myboard.controller.ControllerV2;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
        location = "../resources/images/",
        maxFileSize = -1L,
        maxRequestSize = -1L,
        fileSizeThreshold = 1024
)
@Slf4j
public class BoardRegisterControllerV2 implements ControllerV2 {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "../webapp/images";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("글 등록 처리");
        return null;
    }
}
