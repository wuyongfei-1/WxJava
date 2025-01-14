package me.chanjar.weixin.cp.bean.external;

import me.chanjar.weixin.cp.bean.external.contact.ExternalContact;
import me.chanjar.weixin.cp.bean.external.contact.FollowedUser;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/9/16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpUserExternalContactInfoTest {

  @Test
  public void testFromJson() {
    final String json = "{\n" +
      "  \"errcode\": 0,\n" +
      "  \"errmsg\": \"ok\",\n" +
      "  \"external_contact\": {\n" +
      "    \"external_userid\": \"woAJ2GCAAAXtWyujaWJHDDGi0mACH71w\",\n" +
      "    \"name\": \"李四\",\n" +
      "    \"position\": \"Mangaer\",\n" +
      "    \"avatar\": \"http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0\",\n" +
      "    \"corp_name\": \"腾讯\",\n" +
      "    \"corp_full_name\": \"腾讯科技有限公司\",\n" +
      "    \"type\": 2,\n" +
      "    \"gender\": 1,\n" +
      "    \"unionid\": \"ozynqsulJFCZ2z1aYeS8h-nuasdfR1\",\n" +
      "    \"external_profile\": {\n" +
      "      \"external_attr\": [\n" +
      "        {\n" +
      "          \"type\": 0,\n" +
      "          \"name\": \"文本名称\",\n" +
      "          \"text\": {\n" +
      "            \"value\": \"文本\"\n" +
      "          }\n" +
      "        },\n" +
      "        {\n" +
      "          \"type\": 1,\n" +
      "          \"name\": \"网页名称\",\n" +
      "          \"web\": {\n" +
      "            \"url\": \"http://www.test.com\",\n" +
      "            \"title\": \"标题\"\n" +
      "          }\n" +
      "        },\n" +
      "        {\n" +
      "          \"type\": 2,\n" +
      "          \"name\": \"测试app\",\n" +
      "          \"miniprogram\": {\n" +
      "            \"appid\": \"wx8bd80126147df384\",\n" +
      "            \"pagepath\": \"/index\",\n" +
      "            \"title\": \"my miniprogram\"\n" +
      "          }\n" +
      "        }\n" +
      "      ]\n" +
      "    }\n" +
      "  },\n" +
      "  \"follow_user\": [\n" +
      "    {\n" +
      "      \"userid\": \"rocky\",\n" +
      "      \"remark\": \"李部长\",\n" +
      "      \"description\": \"对接采购事物\",\n" +
      "      \"createtime\": 1525779812\n" +
      "    },\n" +
      "    {\n" +
      "      \"userid\": \"tommy\",\n" +
      "      \"remark\": \"李总\",\n" +
      "      \"description\": \"采购问题咨询\",\n" +
      "      \"createtime\": 1525881637\n" +
      "    }\n" +
      "  ]\n" +
      "}";
    
        final String testJson = "{\n" +
      "   \"errcode\": 0,\n" +
      "   \"errmsg\": \"ok\",\n" +
      "   \"department\": [\n" +
      "       {\n" +
      "           \"id\": 2,\n" +
      "           \"name\": \"广州研发中心\",\n" +
      "           \"name_en\": \"RDGZ\",\n" +
      "           \"department_leader\":[\"zhangsan\",\"lisi\"],\n" +
      "           \"parentid\": 1,\n" +
      "           \"order\": 10\n" +
      "       },\n" +
      "       {\n" +
      "           \"id\": 3,\n" +
      "           \"name\": \"邮箱产品部\",\n" +
      "           \"name_en\": \"mail\",\n" +
      "           \"department_leader\":[\"lisi\",\"wangwu\"],\n" +
      "           \"parentid\": 2,\n" +
      "           \"order\": 40\n" +
      "       }\n" +
      "   ]\n" +
      "}\n";

    // 测试序列化
    val depart = new WxCpDepart();
    depart.setId(8L);
    depart.setName("name");
    depart.setEnName("enName");
    depart.setDepartmentLeader(new String[]{"zhangsan", "lisi"});
    depart.setParentId(88L);
    depart.setOrder(99L);

    String toJson = depart.toJson();

    // 测试企业微信字段返回
    List<WxCpDepart> department = WxCpGsonBuilder.create().fromJson(GsonParser.parse(two).get("department"), new TypeToken<List<WxCpDepart>>() {
    }.getType());

    final WxCpExternalContactInfo contactInfo = WxCpExternalContactInfo.fromJson(json);
    assertThat(contactInfo).isNotNull();
    assertThat(contactInfo.getExternalContact()).isNotNull();

    assertThat(contactInfo.getExternalContact().getExternalUserId()).isEqualTo("woAJ2GCAAAXtWyujaWJHDDGi0mACH71w");
    assertThat(contactInfo.getExternalContact().getPosition()).isEqualTo("Mangaer");
    assertThat(contactInfo.getExternalContact().getAvatar()).isEqualTo("http://p.qlogo.cn/bizmail/IcsdgagqefergqerhewSdage/0");
    assertThat(contactInfo.getExternalContact().getCorpName()).isEqualTo("腾讯");
    assertThat(contactInfo.getExternalContact().getCorpFullName()).isEqualTo("腾讯科技有限公司");
    assertThat(contactInfo.getExternalContact().getType()).isEqualTo(2);
    assertThat(contactInfo.getExternalContact().getGender()).isEqualTo(1);
    assertThat(contactInfo.getExternalContact().getUnionId()).isEqualTo("ozynqsulJFCZ2z1aYeS8h-nuasdfR1");
    assertThat(contactInfo.getExternalContact().getName()).isEqualTo("李四");

    assertThat(contactInfo.getExternalContact().getExternalProfile()).isNotNull();

    final List<ExternalContact.ExternalAttribute> externalAttrs = contactInfo.getExternalContact().getExternalProfile().getExternalAttrs();
    assertThat(externalAttrs).isNotEmpty();

    final ExternalContact.ExternalAttribute externalAttr1 = externalAttrs.get(0);
    assertThat(externalAttr1.getType()).isEqualTo(0);
    assertThat(externalAttr1.getName()).isEqualTo("文本名称");
    assertThat(externalAttr1.getText().getValue()).isEqualTo("文本");

    final ExternalContact.ExternalAttribute externalAttr2 = externalAttrs.get(1);
    assertThat(externalAttr2.getType()).isEqualTo(1);
    assertThat(externalAttr2.getName()).isEqualTo("网页名称");
    assertThat(externalAttr2.getWeb().getUrl()).isEqualTo("http://www.test.com");
    assertThat(externalAttr2.getWeb().getTitle()).isEqualTo("标题");

    final ExternalContact.ExternalAttribute externalAttr3 = externalAttrs.get(2);
    assertThat(externalAttr3.getType()).isEqualTo(2);
    assertThat(externalAttr3.getName()).isEqualTo("测试app");
    assertThat(externalAttr3.getMiniProgram().getAppid()).isEqualTo("wx8bd80126147df384");
    assertThat(externalAttr3.getMiniProgram().getPagePath()).isEqualTo("/index");
    assertThat(externalAttr3.getMiniProgram().getTitle()).isEqualTo("my miniprogram");


    List<FollowedUser> followedUsers = contactInfo.getFollowedUsers();
    assertThat(followedUsers).isNotEmpty();
    assertThat(followedUsers.get(0).getUserId()).isEqualTo("rocky");
    assertThat(followedUsers.get(0).getRemark()).isEqualTo("李部长");
    assertThat(followedUsers.get(0).getDescription()).isEqualTo("对接采购事物");
    assertThat(followedUsers.get(0).getCreateTime()).isEqualTo(1525779812);

    assertThat(followedUsers.get(1).getUserId()).isEqualTo("tommy");
    assertThat(followedUsers.get(1).getRemark()).isEqualTo("李总");
    assertThat(followedUsers.get(1).getDescription()).isEqualTo("采购问题咨询");
    assertThat(followedUsers.get(1).getCreateTime()).isEqualTo(1525881637);
  }
}
