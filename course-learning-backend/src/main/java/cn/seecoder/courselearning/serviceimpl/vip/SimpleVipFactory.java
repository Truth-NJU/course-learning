package cn.seecoder.courselearning.serviceimpl.vip;

import cn.seecoder.courselearning.dto.vip.VipChargeDTO;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.vo.vip.VipChargeVO;
import cn.seecoder.courselearning.vo.vip.VipInfoVO;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleVipFactory {
    public static VipChargeVO getVipByType(VipChargeDTO vipChargeDTO) {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "xml/vip.xml"));
            NodeList nodeList = doc.getElementsByTagName(vipChargeDTO.getType());
            Node vipTypeNode = nodeList.item(0).getChildNodes().item(1);
            int money = Integer.parseInt(vipTypeNode.getFirstChild().getNodeValue());
            Node vipTimeNode = nodeList.item(0).getChildNodes().item(3);
            int time = Integer.parseInt(vipTimeNode.getFirstChild().getNodeValue());
            Date endTime = new Date(time * 24 * 3600 * 1000);
            return new VipChargeVO(vipChargeDTO.getType(), money, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return new VipChargeVO();
        }
    }

    public static List<VipInfoVO> getVipPrice() {
        List<VipInfoVO> vipInfoVOList = new ArrayList<>();
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "xml/vip.xml"));
            NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
            for (int i = 1; i < nodeList.getLength(); i += 2) {
                Node node = nodeList.item(i);
                NodeList childNodeList = node.getChildNodes();
                vipInfoVOList.add(new VipInfoVO(node.getNodeName(),
                        Integer.parseInt(childNodeList.item(1).getFirstChild().getNodeValue()),
                        Integer.parseInt(childNodeList.item(3).getFirstChild().getNodeValue()),
                        Integer.parseInt(childNodeList.item(5).getFirstChild().getNodeValue())
                        ));
            }
            return vipInfoVOList;
        } catch (Exception e) {
            e.printStackTrace();
            return vipInfoVOList;
        }
    }
}
