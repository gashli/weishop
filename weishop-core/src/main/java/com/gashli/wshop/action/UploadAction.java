//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends ActionSupport {
    private static final long serialVersionUID = -4848248679889814408L;
    private List<File> Filedata;
    private List<String> FiledataFileName;
    private List<String> FiledataContentType;
    private String name;

    public UploadAction() {
    }

    public void doUpload() {
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext)ac.get("com.opensymphony.xwork2.dispatcher.ServletContext");
        String savePath = sc.getRealPath("/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String path = "upload/" + ymd + "/";
        savePath = savePath + path;
        File f1 = new File(savePath);
        if(!f1.exists()) {
            f1.mkdirs();
        }

        int size = this.Filedata.size();
        String extName = null;
        String name = null;

        for(int response = 0; response < size; ++response) {
            extName = ((String)this.FiledataFileName.get(response)).substring(((String)this.FiledataFileName.get(response)).lastIndexOf("."));
            name = UUID.randomUUID().toString();
            File e = new File(savePath + name + extName);

            try {
                FileUtils.copyFile((File)this.Filedata.get(response), e);
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }

        HttpServletResponse var18 = ServletActionContext.getResponse();

        try {
            JSONObject var17 = new JSONObject();
            var17.put("statusCode", 200);
            var17.put("message", "");
            var17.put("filename", path + name + extName);
            var18.getWriter().print(var17.toString());
            var18.getWriter().flush();
            var18.getWriter().close();
        } catch (IOException var14) {
            var14.printStackTrace();
        } catch (JSONException var15) {
            var15.printStackTrace();
        }

    }

    public List<File> getFiledata() {
        return this.Filedata;
    }

    public void setFiledata(List<File> filedata) {
        this.Filedata = filedata;
    }

    public List<String> getFiledataFileName() {
        return this.FiledataFileName;
    }

    public void setFiledataFileName(List<String> filedataFileName) {
        this.FiledataFileName = filedataFileName;
    }

    public List<String> getFiledataContentType() {
        return this.FiledataContentType;
    }

    public void setFiledataContentType(List<String> filedataContentType) {
        this.FiledataContentType = filedataContentType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
