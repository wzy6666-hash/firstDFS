package com.qf.Controller;
import com.qf.pojo.TbItem;
import com.qf.service.TbItemService;
import com.qf.utils.FastDFSClient;
import com.qf.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.util.List;
@Controller
public class TbItemController {
    @Resource
    private TbItemService tbItemService;
    @RequestMapping("/findall")
    @ResponseBody
    public R findAll(){
        System.out.println("*********");
        List<TbItem> list=tbItemService.findAll();
        return R.ok().put("tbItems",list);
    }
    @RequestMapping("/doAdd.do")
    @ResponseBody
    public R add(@RequestParam(name = "img") MultipartFile file,TbItem tbItem){
        //得到图片名

        StringBuilder stringBuilder=new StringBuilder("http://192.168.168.168/");
        try {
            String filename = file.getOriginalFilename();

            String substring = filename.substring(filename.lastIndexOf("."));
            FastDFSClient client=new FastDFSClient("client.conf");
            String[] uploadFile = client.uploadFile(file.getBytes(), substring);
            for (int i=0;i<uploadFile.length;i++) {
                stringBuilder.append(uploadFile[i]);
                if (i==0){
                    stringBuilder.append(File.separator);
                }
                System.out.println("成功"+stringBuilder);
            }
        } catch (Exception e) {
            System.out.println("失败");
            e.printStackTrace();
        }
        String images = stringBuilder.toString();
        tbItem.setImages(images);
        boolean save = tbItemService.save(tbItem);
        if (save){
            return  R.ok();
        }else {
            return R.error();
        }
    }
}
