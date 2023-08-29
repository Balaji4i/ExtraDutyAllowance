package view;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.TimeZone;

public class ApprovalPayload {
    public ApprovalPayload() {
        super();
    }
   
    
    //bUSINESS
     /***Cloud purpose un comment these section while deploying to TEST cloud **/ 
     public static final String DUTY_ALLOWANCE_WSDL = "http://oaosoatst-wls-1.sub08071802371.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/ExtraDutyAllowanceApproval/allowanceapprovalprocess_client_ep?WSDL";
    //public static final String DUTY_ALLOWANCE_WSDL ="https://oaosoatest.oandoplc.com/soa-infra/services/default/ExtraDutyAllowanceApproval/allowanceapprovalprocess_client_ep?WSDL";
    public static final String DUTY_ALLOWANCE_METHOD = "process";
   
    /***Cloud purpose un comment these section while deploying to PROD cloud **/    
   //public static final String DUTY_ALLOWANCE_WSDL ="http://oaosoaprd-wls-1.sub08071802370.oandopaasvcn.oraclevcn.com:9073/soa-infra/services/default/ExtraDutyAllowanceApproval/allowanceapprovalprocess_client_ep?WSDL";
    
    
    public static String businessTypeXMLData(String p_EmployeeName,String p_EmployeeNumber,String p_Email,
                                             String p_BusinessGroup,String p_ExtraDutyNo,String p_TransDate,
                                             String p_TotAllowancAmount,String p_ApprovalStatus,String p_Comments,String p_ApproverComments,
                                             ArrayList p_AllowanceTyp,ArrayList p_StartDate,ArrayList p_EndDate,
                                             ArrayList p_Days,ArrayList p_AllowancAmount,
                                             ArrayList p_Rate ,ArrayList p_CommentsLine
                                               )
                                              {
        String[] info=payloadHeader();  
        String totalXml=null;
        String xmlData2="\n";
        System.err.println("Created time===>"+info[0]);
        System.err.println("User===========>"+info[1]);
        System.err.println("Password=======>"+info[2]);
        System.err.println("End time=======>"+info[3]);  
        String xmlData="<soapenv:Envelope xmlns:ext=\"http://xmlns.oracle.com/extradutyallowanceapproval\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
        "   <soapenv:Header/>\n" +  
        "   <soapenv:Body>\n" + 
               " <ext:processRequest>\n" + 
               "         <ext:EmployeeName>"+p_EmployeeName+"</ext:EmployeeName>\n" + 
               "         <ext:EmployeeNumber>"+p_EmployeeNumber+"</ext:EmployeeNumber>\n" + 
               "         <ext:EmailAddress>"+p_Email+"</ext:EmailAddress>\n" + 
               "         <ext:BusinessGroup>"+p_BusinessGroup+"</ext:BusinessGroup>\n" + 
               "         <ext:ExtraDutyAllowanceID>"+p_ExtraDutyNo+"</ext:ExtraDutyAllowanceID>\n" +
               "         <ext:TransactionDate>"+p_TransDate+"</ext:TransactionDate>\n" +
               "         <ext:TotalAllowanceAmount>"+p_TotAllowancAmount+"</ext:TotalAllowanceAmount>\n" + 
               "         <ext:Status>"+p_ApprovalStatus+"</ext:Status>\n" + 
               "         <ext:Remarks>"+p_Comments+"</ext:Remarks>\n" +
        "         <ext:ApproverComments>"+p_ApproverComments+"</ext:ApproverComments>\n" +
        "         <!--1 or more repetitions:-->\n" ;
        for(int i=0;i<p_AllowanceTyp.size() ;i++){ 
             String tempXml=
               "         <ext:processLines>\n" + 
                       "         <ext:AllowanceType>"+p_AllowanceTyp.get(i)+"</ext:AllowanceType>\n" + 
                       "         <ext:StartDate>"+p_StartDate.get(i)+"</ext:StartDate>\n" + 
                       "         <ext:EndDate>"+p_EndDate.get(i)+"</ext:EndDate>\n" + 
                       "         <ext:AllowanceDays>"+p_Days.get(i)+"</ext:AllowanceDays>\n" + 
                       "         <ext:AllowanceAmount>"+p_AllowancAmount.get(i)+"</ext:AllowanceAmount>\n" + 
                       "         <ext:AllowanceRate>"+p_Rate.get(i)+"</ext:AllowanceRate>\n" + 
                       "         <ext:Comments>"+p_CommentsLine.get(i)+"</ext:Comments>\n" +
               "         </ext:processLines>\n" ;
             xmlData2=xmlData2+"\n"+tempXml;
                   }
               String xmlData3 =  "      </ext:processRequest>\n" + 
               "   </soapenv:Body>\n" + 
               "</soapenv:Envelope>";
               totalXml= xmlData+xmlData2+"\n"+xmlData3;
               System.err.println("Totalxml"+totalXml);
               return totalXml;
       
    }


    public static String[] payloadHeader() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'"); //Hours:Minutes:Seconds
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.util.Date expDate;
        expDate = new java.util.Date(t + (10 * 600000000));
        String[] headerInfo = new String[4];
        headerInfo[0] = dateFormat.format(date);
        headerInfo[1] = "oaopdtst";
        headerInfo[2] = "O_0Pst#819";
        headerInfo[3] = dateFormat.format(expDate);
        return headerInfo;
    }
    
    public static String getUser(){        
        return null;
    }    
}