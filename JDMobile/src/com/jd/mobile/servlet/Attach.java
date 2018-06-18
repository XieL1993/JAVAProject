package com.jd.mobile.servlet;

import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;
import com.jd.mobile.service.impl.ProductServiceImpl;
import com.jd.mobile.utils.BeanFactory;
import com.jd.mobile.utils.ResponseResult;
import com.jd.mobile.utils.UUIDUtils;
import com.jd.mobile.utils.UploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Attach",urlPatterns = "/attach/add")
public class Attach extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Map<String,Object> map = new HashMap<>();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item:list){
                if(item.isFormField()){
                    map.put(item.getFieldName(),item.getString("utf-8"));
                }else {
                    String oldFileName = item.getName();
                    String newFileName = UploadUtils.getUUIDName(oldFileName);
                    // 创建目录
                    String dir = UploadUtils.getDir(newFileName);
                    String realPath =  getServletContext().getRealPath("/image/product")+dir;
                    File filePath = new File(realPath);
                    if(!filePath.exists()){
                        filePath.mkdirs();
                    }
                    // 创建文件
                    File file = new File(filePath,newFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    // 读写
                    InputStream is = item.getInputStream();
                    FileOutputStream os = new FileOutputStream(file);
                    IOUtils.copy(is,os);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
                    map.put("pimage","/product"+dir+"/"+newFileName);
                }
            }
            Product product = BeanFactory.populateMap(Product.class, map);
            product.setPid(UUIDUtils.getUUID());
            product.setPdate(new Date());
            product.setPflag(0);
            ProductService service = new ProductServiceImpl();
            service.addProduct(product);
            ResponseResult.success(resp,"","新增产品成功");
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult.error(resp, e);
        }
    }
}
