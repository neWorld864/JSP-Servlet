/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2021-04-05 14:57:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import member.model.vo.Member;

public final class memberPwdUpdateForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/member/../common/menubar.jsp", Long.valueOf(1617594403539L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<style>\r\n");
      out.write(".outer{\r\n");
      out.write("\t\twidth: 600px; height: 500px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white; \r\n");
      out.write("\t\tcolor: black; margin-left: auto; margin-right: auto; margin-top: 50px;\r\n");
      out.write("\t}\r\n");
      out.write("\t#updatePwdForm td{text-align: right; height: 50px;}\r\n");
      out.write("\t#updatePwdBtn{background: #D1B2FF;}\r\n");
      out.write("\t#cancelBtn{background: #B2CCFF;}\r\n");
      out.write("\t#updatePwdForm>table{margin: auto;}\r\n");
      out.write("</style>\r\n");
      out.write("<!-- 비밀번호가 같은지 다른지 확인하는 유효성검사도 넣기 -->\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write('\r');
      out.write('\n');

	/* loginUser 만들기 */
	Member loginUser = (Member)session.getAttribute("loginUser"); /* session에 데이터 저장했으므로 */

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>JSP&Servlet</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print( request.getContextPath() );
      out.write("/js/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("\tbody{\r\n");
      out.write("\t\tbackground:url('");
      out.print( request.getContextPath() );
      out.write("/images/bg.png') no-repeat center center fixed;\r\n");
      out.write("\t\tbackground-size: cover;\r\n");
      out.write("\t}\r\n");
      out.write("\t.loginArea{float: right;}\r\n");
      out.write("\t#loginTable{text-align: right;}\r\n");
      out.write("\t#loginTable td:nth-child(1){padding-right: 15px;}\r\n");
      out.write("\t.loginBtns{float: right; margin-left: 5px;}\r\n");
      out.write("\t#loginBtn, #myPage{background: #D1B2FF;}\r\n");
      out.write("\t#joinBtn, #logout{background: #B2CCFF;}\r\n");
      out.write("\tinput[type=button], input[type=submit]{cursor: pointer; border-radius: 15px; color: white;}\r\n");
      out.write("\t#userInfo label{font-weight: bold;}\r\n");
      out.write("\r\n");
      out.write("\t.wrap{background: white; width: 100%; height: 50px;}\r\n");
      out.write("\t.menu{\r\n");
      out.write("\t\tbackground: white; color: navy; text-align: center; font-weight: bold; \r\n");
      out.write("\t\tvertical-align: middle; width: 150px; height: 50px; display: table-cell;\r\n");
      out.write("\t}\r\n");
      out.write("\tnav{width: 600px; margin-left: auto; margin-right: auto;}\r\n");
      out.write("\t.menu:hover {background: beige; color:orangered; font-weight:bold; cursor:pointer;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1 align=\"center\">Welcome to JSP&amp;Servlet World!</h1>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"loginArea\">\r\n");
      out.write("\t\t");
 if(loginUser == null){ 
      out.write("\r\n");
      out.write("\t\t\t<form id=\"loginForm\" action=\"");
      out.print( request.getContextPath() );
      out.write("/login.me\" method=\"post\" onsubmit=\"\" return validate();>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<!-- 루트 컨텍스트?를 받아오는 역할 --><!-- 로그인을 처리하는 url --> <!-- 아이디, 비밀번호 입력하지 않으면 넘어가지 않도록 -->\r\n");
      out.write("\t\t\t\t<!-- 로그인 유저가 null인 경우에만 떠야함 -->\r\n");
      out.write("\t\t\t\t<table id=\"loginTable\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>ID</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"text\" name=\"userId\" id=\"userId\"></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td><label>PWD</label></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"password\" name=\"userPwd\" id=\"userPwd\"></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<div class=\"loginBtns\">\r\n");
      out.write("\t\t\t\t\t<input type=\"submit\" id=\"loginBtn\" value=\"로그인\">\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"joinBtn\" value=\"회원가입\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t");
 } else{ 
      out.write("\r\n");
      out.write("\t\t\t<div id=\"userInfo\" align=\"right\">\r\n");
      out.write("\t\t\t\t<label>");
      out.print( loginUser.getUserName() );
      out.write("님의 방문을 환영합니다.</label>\r\n");
      out.write("\t\t\t\t<br clear=\"all\">\r\n");
      out.write("\t\t\t\t<div class=\"loginBtns\">\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"myPage\" value=\"내 정보보기\">\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"logout\" onclick=\"logout();\" value=\"로그아웃\">\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<br clear=\"all\">\r\n");
      out.write("\t<br>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"wrap\">\r\n");
      out.write("\t <nav>\r\n");
      out.write("\t \t<div class=\"menu\" onclick=\"goHome();\">Home</div>\r\n");
      out.write("\t \t<div class=\"menu\" onclick=\"goNotice();\">공지사항</div>\r\n");
      out.write("\t \t<div class=\"menu\" onclick=\"goBoard();\">게시판</div>\r\n");
      out.write("\t \t<div class=\"menu\" onclick=\"goThumbnail();\">사진 게시판</div>\r\n");
      out.write("\t \t\r\n");
      out.write("\t </nav>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tfunction validate(){\r\n");
      out.write("\t\t\tvar id = $('#userId');\r\n");
      out.write("\t\t\tvar pwd = $('#userPwd');\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(id.val().trim().length == 0){\r\n");
      out.write("\t\t\t\talert(\"아이디를 입력해주세요.\");\r\n");
      out.write("\t\t\t\tid.focus();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t} else if(pwd.val().trim().length) {\r\n");
      out.write("\t\t\t\talert(\"비밀번호를 입력해주세요.\");\r\n");
      out.write("\t\t\t\tpwd.focus();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction logout() {\r\n");
      out.write("\t\t\tlocation.href='");
      out.print( request.getContextPath() );
      out.write("/logout.me';\r\n");
      out.write("\t\t\t/* 자바스크립트 안에 스크립트립을 넣었다고 해서 스크립트립안에 자바스크립트 넣으려고 하면 안 됨!! */\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#joinBtn').on('click', function(){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("/signUpForm.me\";\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#myPage').on('click', function(){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("/mypage.me\";\r\n");
      out.write("\t\t})\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goHome (){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goNotice (){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("/list.no\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goNotice (){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("/list.bo\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goNotice (){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.print( request.getContextPath() );
      out.write("/list.th\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<br clear=\"all\">\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"outer\">\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t<h2 align=\"center\">비밀번호 수정하기</h2>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<form action=\"");
      out.print( request.getContextPath() );
      out.write("/updatePwd.me\" method=\"post\" id=\"updatePwdForm\" name=\"updatePwdForm\" onsubmit=\"return send();\">\r\n");
      out.write("\t\t<!-- 비밀번호수정3) UpdatePwdServlet(updatePwd.me) 만들기 -->\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><label>현재 비밀번호</label></td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"userPwd\" id=\"userPwd\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><label>변경 비밀번호</label></td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"newPwd\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td><label>변경 비밀번호 확인</label></td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"password\" name=\"newPwd2\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<br><br>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div class=\"btns\" align=\"center\">\r\n");
      out.write("\t\t\t\t<input id=\"updatePwdBtn\" type=\"submit\" value=\"변경하기\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" id=\"cancelBtn\" onclick=\"location.href='javascript:history.back();'\" value=\"취소하기\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tfunction send(){\r\n");
      out.write("\t\t\tvar newPwd = $('input[name=\"newPwd\"]');\r\n");
      out.write("\t\t\tvar newPwd2 = $('input[name=\"newPwd2\"]');\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(newPwd.val().trim() != newPwd2.val().trim()){\r\n");
      out.write("\t\t\t\talert('비밀번호가 다릅니다.');\r\n");
      out.write("\t\t\t\tnewPwd2.val('');\r\n");
      out.write("\t\t\t\tnewPwd2.focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t} else if(newPwd.val().trim() == '' || newPwd2.val().trim() == ''){\r\n");
      out.write("\t\t\t\talert('비밀번호를 입력해주세요');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
