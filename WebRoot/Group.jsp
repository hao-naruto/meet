<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <link rel="StyleSheet" href="./css/style.css" type="text/css" media="screen">
    <link rel="StyleSheet" href="./css/style2.css" type="text/css" media="screen">
    <link rel="StyleSheet" href="./css/style4.css" type="text/css" media="screen">
    <script src="./js/moment.js"></script>
    <script src="./js/moment-timezone-with-data-2012-2022.min.js"></script>
    <script src="./js/jquery-1.6.2.min.js"></script>
    <script src="./js/prototype.js"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor=black onload="PageLoaded();">


<div id=MainBody  style="background:white; margin:0px;border:0px;padding:0px;">


<script type="text/javascript">
jQuery.noConflict();

var AnchorRow=0;
var AnchorColumn=0;
var HoverRow=0;
var HoverColumn=0;
var WriteMode="";
var Dates = [];
var xMousePos;
var yMousePos;
var xMouseRef;
var yMouseRef;
var count=0;
var Months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var DaysOfWeek = ["S","M","T","W","R","F","S"];

if (document.layers) { // Netscape
    document.captureEvents(Event.MOUSEMOVE);
    document.onmousemove = captureMousePosition;
} else if (document.all) { // Internet Explorer
    document.onmousemove = captureMousePosition;
} else if (document.getElementById) { // Netscape 6
    document.onmousemove = captureMousePosition;
}

var mousewheelevt=(/Firefox/i.test(navigator.userAgent))? "DOMMouseScroll" : "mousewheel" //FF doesn't recognize mousewheel as of FF3.x

function wheel(event){
    var evt=window.event || event //equalize event object
    var delta=evt.detail? evt.detail*(-240) : evt.wheelDelta
    delta = delta/Math.abs(delta);
//    ShiftCalendar(-7*delta);
}

function captureMousePosition(e) {
    if (document.layers) {
        xMousePos = e.pageX;
        yMousePos = e.pageY;
        xMousePosMax = window.innerWidth+window.pageXOffset;
        yMousePosMax = window.innerHeight+window.pageYOffset;
    } else if (document.all) {
        xMousePos = window.event.x+document.body.scrollLeft;
        yMousePos = window.event.y+document.body.scrollTop;
        xMousePosMax = document.body.clientWidth+document.body.scrollLeft;
        yMousePosMax = document.body.clientHeight+document.body.scrollTop;
    } else if (document.getElementById) {
        xMousePos = e.pageX;
        yMousePos = e.pageY;
        xMousePosMax = window.innerWidth+window.pageXOffset;
        yMousePosMax = window.innerHeight+window.pageYOffset;
    }
}

function MouseDownDate(Row,Column) {
    IsMouseDownDates=true;
    AnchorRow=Row;
    AnchorColumn=Column;
    HoverRow=Row;
    HoverColumn=Column;
    if (ArraySearch(Dates,document.getElementById("DateOf-"+Row+"-"+Column).value)) {
        WriteMode = "erase";
    } else {
        WriteMode = "write";
    }
    ReColor();
}

function MouseOverDate(Row,Column) {
    if (IsMouseDownDates) {
        HoverRow=Row;
        HoverColumn=Column;
        ReColor();
    }
}

function AddHighlightedDates(HighlightedDates) {
    var DateNum=-1;
    while (DateNum < HighlightedDates.length) {
        Dates.push(HighlightedDates[DateNum]);
        DateNum++;
    }
    Dates = Unique(Dates);
    document.getElementById("PossibleDates").value = Dates.join("|");
}

function RemoveHighlightedDates(HighlightedDates) {
    var DateNum=-1;
    NewDates = [];
    while (DateNum < Dates.length) {
        if (!ArraySearch(HighlightedDates,Dates[DateNum])) {
            NewDates.push(Dates[DateNum]);
        }
        DateNum++;
    }
    Dates = Unique(NewDates);
    document.getElementById("PossibleDates").value = Dates.join("|");
}

function Unique(InputArray) {
    var a = [];
    for(var i=0; i<InputArray.length; i++) {
        unique = 1;
        for(var j=i+1; j<InputArray.length; j++) {
            if (InputArray[i] == InputArray[j]) {unique = 0;}
        }
        if (unique&&(InputArray[i]!=null)&&(InputArray[i]!="")) { a.push(InputArray[i]); }
    }
    return a.sort();
}

function MouseUp() {

    if (WriteMode!="") {
        var HighlightedDates = [];
        for (Row=1;Row<=6;Row++) {
            for (Column=1;Column<=7;Column++) {
                if (((AnchorRow-Row)*(Row-HoverRow)>=0) && ((AnchorColumn-Column)*(Column-HoverColumn)>=0)) {
                    HighlightedDates.push(document.getElementById("DateOf-"+Row+"-"+Column).value);
                }
                document.getElementById("Day-"+Row+"-"+Column).style.border='1px solid black';
                document.getElementById("Day-"+Row+"-"+Column).style.padding='2px';
                document.getElementById("Day-"+Row+"-"+Column).style.margin='1px';
            }
        }

        if (WriteMode == "write") {
            AddHighlightedDates(HighlightedDates);
        } else { // WriteMode == "erase"
            RemoveHighlightedDates(HighlightedDates);
        }
    }
    WriteMode = "";

    IsMouseDownDates=false;
    IsMouseDownSideLabel=false;
    IsMouseDownTopLabel=false;

}

function ArraySearch(arr, obj) {
    for(var i=0; i<arr.length; i++) {
        if (arr[i] == obj) return true;
    }
    return false;
}

function ShiftCalendar(Delta) {
    for (Row=1;Row<=5;Row++) {
        var ThisWeek=false;
        for (Column=1;Column<=7;Column++) {
            var DateOfCell = document.getElementById("DateOf-"+Row+"-"+Column).value.split("-");
            t = new Date(DateOfCell[0],DateOfCell[1]-1,DateOfCell[2],12,0);
            t.setTime(t.getTime()+60*60*24*1000*Delta);
            var Month = t.getMonth()+1;
            if (Month<10) { Month = '0'+Month; }

            var DayOfMonth = t.getDate();
            if (DayOfMonth<10) { DayOfMonth = '0'+DayOfMonth; }

            var FullDateString = t.getFullYear() + "-" + Month + "-" + DayOfMonth;

            document.getElementById("DateOf-"+Row+"-"+Column).value = FullDateString;
            if ((Row==1)&&(Column==1)) {
                document.getElementById("TopLeftDate").value = FullDateString;
            }
            if (FullDateString == DateLoaded) {
                document.getElementById("Day-"+Row+"-"+Column).style.fontWeight = "bold";
                ThisWeek=true;
            } else {
                document.getElementById("Day-"+Row+"-"+Column).style.fontWeight = "normal";
            }
            document.getElementById("Day-"+Row+"-"+Column).innerHTML = t.getDate();

        }

        var DateOfCell = document.getElementById("DateOf-"+Row+"-1").value.split("-");
        var DateOfCell2 = document.getElementById("DateOf-"+Row+"-7").value.split("-");
        var MonthName; var Year;

        if (DateOfCell[1]==DateOfCell2[1]) {
            MonthName = Months[DateOfCell[1]-1];
        } else {
            MonthName = Months[DateOfCell[1]-1]+"/"+Months[DateOfCell2[1]-1];
        }
        if (ThisWeek) {
            document.getElementById("Month-"+Row).innerHTML = "<b>"+ MonthName +"</b>";
        } else {
            document.getElementById("Month-"+Row).innerHTML = MonthName;
        }

        if (DateOfCell[0]==DateOfCell2[0]) {
            Year = DateOfCell[0];
        } else {
            Year = DateOfCell[0]+"/"+DateOfCell2[0];
        }
        if (ThisWeek) {
            document.getElementById("Year-"+Row).innerHTML = "<b>"+ Year +"</b>";
        } else {
            document.getElementById("Year-"+Row).innerHTML = Year;
        }

    }

    for (Column=1;Column<=7;Column++) {
        var DateOfCell = document.getElementById("DateOf-1-"+Column).value.split("-");
        var t = new Date(DateOfCell[0],DateOfCell[1]-1,DateOfCell[2],12,0);
        document.getElementById("DayOfWeek-"+Column).innerHTML = DaysOfWeek[t.getDay()];
        document.getElementById("DateOf-6-"+Column).value = t.getDay();
    }

    ReColor();
}

function ShiftToDate(NewTopLeftDate,ShiftToLeft) {
    var CalendarStartTimeString = document.getElementById("DateOf-1-1").value.split("-");
    CalendarStartTime = new Date(CalendarStartTimeString[0],CalendarStartTimeString[1]-1,CalendarStartTimeString[2],12,0);
    var NewStartTimeString = NewTopLeftDate.split("-");
    NewStartTime = new Date(NewStartTimeString[0],NewStartTimeString[1]-1,NewStartTimeString[2],12,0);

    ShiftDays = (NewStartTime.getTime() - CalendarStartTime.getTime())/(60*60*24*1000);
    if (ShiftToLeft) {
        ShiftCalendar(ShiftDays);
    } else {
        ShiftCalendar(7*Math.floor(ShiftDays/7));
    }
}

function ReColorOld() {
    for (Row=1;Row<=6;Row++) {
        for (Column=1;Column<=7;Column++) {
            if ((WriteMode!="") && ((AnchorRow-Row)*(Row-HoverRow)>=0) && ((AnchorColumn-Column)*(Column-HoverColumn)>=0)) {

                if (WriteMode == "write") {
                    document.getElementById("Day-"+Row+"-"+Column).style.backgroundColor='green';
                    document.getElementById("Day-"+Row+"-"+Column).style.color='white';
                } else {
                    document.getElementById("Day-"+Row+"-"+Column).style.backgroundColor='#ffdede';
                    document.getElementById("Day-"+Row+"-"+Column).style.color='black';
                }
                document.getElementById("Day-"+Row+"-"+Column).style.border='0px solid black';
                document.getElementById("Day-"+Row+"-"+Column).style.padding='4px';
                document.getElementById("Day-"+Row+"-"+Column).style.margin='0px';
            } else {
                document.getElementById("Day-"+Row+"-"+Column).style.border='1px solid black';
                document.getElementById("Day-"+Row+"-"+Column).style.padding='2px';
                document.getElementById("Day-"+Row+"-"+Column).style.margin='1px';

                if (ArraySearch(Dates,document.getElementById("DateOf-"+Row+"-"+Column).value)) {
                    document.getElementById("Day-"+Row+"-"+Column).style.backgroundColor='green';
                    document.getElementById("Day-"+Row+"-"+Column).style.color='white';
                } else {
                    document.getElementById("Day-"+Row+"-"+Column).style.backgroundColor='#ffdede';
                    document.getElementById("Day-"+Row+"-"+Column).style.color='black';
                }

            }
        }
    }
}

function DateTypesChanged() {
    if (document.getElementById("DateTypes").value == "SpecificDates") {
        document.getElementById("SpecificDates").style.display = '';
        document.getElementById("DaysOfTheWeek").style.display = 'none';
        document.getElementById("Today").style.display = '';
    } else {
        document.getElementById("SpecificDates").style.display = 'none';
        document.getElementById("DaysOfTheWeek").style.display = '';
        document.getElementById("Today").style.display = 'none';
    }
}

function PageLoaded() {
    var select = document.getElementById("ParticipantTimeZone");
    timezones = moment.tz.names();
    var timezone = moment.tz.guess();
    document.getElementById("ParticipantTimeZone").style.display='';
    document.getElementById("YourTimeZone").innerHTML = "Your Time Zone: ";

    for(var i = 0; i < timezones.length; i++) {
        var opt = timezones[i];
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.appendChild(el);
        if (timezone == opt) select.options[i].selected = true;
    }
    if (timezone != "Asia/Shanghai") {
        LoadAvailabilityGrids();
    }
}

function LoadAvailabilityGrids() {
    participantTimeZone = document.getElementById("ParticipantTimeZone").selectedOptions[0].value;
    new Ajax.Request("AvailabilityGrids.php", {
        method: "post",
        parameters: "id=<c:out value='${ NewEventName}'></c:out>&code=OG9tc&participantTimeZone=" + participantTimeZone,
        asynchronous:true,
        onSuccess:function(t) {
            document.getElementById("AvailabilityGrids").innerHTML = t.responseText;
            if (UserID) {
                document.getElementById("SignIn").style.display='none';
                document.getElementById("YouGrid").style.display='';
                document.getElementById("UserName").innerHTML=PeopleNames[PeopleIDs.indexOf(UserID)];
                ReColorIndividual();
            }
        }
    });

}


/******* STUFF WRITTEN FOR NEW VERSION *******/


document.ondragstart=function(){return false;};
document.onmouseup=SelectStop;
var ChangeToAvailable=false;
var IsMouseDown=false;
var PeopleNames=new Array();
var PeopleIDs=new Array();
var UserID=0;
var TimeOfSlot=new Array();
var AvailableAtSlot=new Array();
var Col = new Array();
var Row = new Array();
var FromCol=-1; var ToCol=-1;
var FromRow=-1; var ToRow=-1;


function ReColorIndividual() {
    for (var i=0;i<TimeOfSlot.length;i++) {
        if (FromCol<ToCol) { ColA=FromCol; ColB=ToCol; } else { ColA=ToCol; ColB=FromCol; }
        if (FromRow<ToRow) { RowA=FromRow; RowB=ToRow; } else { RowA=ToRow; RowB=FromRow; }

        var currentElement = document.getElementById("YouTime"+TimeOfSlot[i]);
        var dataCol = Number(currentElement.getAttribute("data-col"));
        var dataRow = Number(currentElement.getAttribute("data-row"));

        var WithinX = ((ColA<=dataCol)&&(dataCol<=ColB));
        var WithinY = ((RowA<=dataRow)&&(dataRow<=RowB));

        if (ChangeToAvailable) NewColor="#339900"; else NewColor="#ffdede";
        if (WithinX && WithinY && IsMouseDown) {
            currentElement.style.background=NewColor;
            currentElement.style.borderColor=NewColor;
            if (ColA==dataCol) currentElement.style.borderLeftColor="black";
            if (ColB==dataCol) currentElement.style.borderRightColor="black";
            if (RowA==dataRow) currentElement.style.borderTopColor="black";
            if (RowB==dataRow) currentElement.style.borderBottomColor="black";
        } else {
            if (-1 != AvailableAtSlot[i].indexOf(UserID) ) {
                currentElement.style.background="#339900";
                currentElement.style.borderColor="black";
            } else {
                currentElement.style.background="#ffdede";
                currentElement.style.borderColor="black";
            }
        }
    }
}

function ReColorGroup() {
    var MinAvailable = 10000; var MaxAvailable=0;
    var SelfIsAvailable = false;
    for (var i=0;i<AvailableAtSlot.length;i++) {
        if (AvailableAtSlot[i].length<MinAvailable) MinAvailable = AvailableAtSlot[i].length;
        if (AvailableAtSlot[i].length>MaxAvailable) MaxAvailable = AvailableAtSlot[i].length;
        if (AvailableAtSlot[i].indexOf(UserID)!=-1) SelfIsAvailable = true;
    }
    if ((AvailableIDs.indexOf(UserID)!=-1)&&(!SelfIsAvailable)) {
        SplitSpot = AvailableIDs.indexOf(UserID);
        AvailableIDs.splice(SplitSpot,1);
    } else if ((AvailableIDs.indexOf(UserID)==-1)&&SelfIsAvailable)
        AvailableIDs.push(UserID);

    document.getElementById("MinAvailable").innerHTML = MinAvailable+"/"+AvailableIDs.length;
    document.getElementById("MaxAvailable").innerHTML = MaxAvailable+"/"+AvailableIDs.length;

    TableStart = "<table width=100 height=10 cellpadding=0 cellspacing=0 style='border: solid 1px black'><tr>";
    TableEnd = "</tr></table>";
    TableContent = "";
    for (var i=MinAvailable; i<=MaxAvailable; i++) {
        Red = Green = Blue = Math.round(255/2);
        if (MinAvailable!=MaxAvailable) {
            Red = Math.round(((204)*(MaxAvailable-i)/(MaxAvailable-MinAvailable))+51);
            Green = Math.round(((102)*(MaxAvailable-i)/(MaxAvailable-MinAvailable))+153);
            Blue = Math.round(((255)*(MaxAvailable-i)/(MaxAvailable-MinAvailable))+0);
        }

        TableContent += "<td bgcolor=#" + (Red*256*256+Green*256+Blue).toString(16) + ">&nbsp;</td>";
    }

    document.getElementById("GroupKey").innerHTML = TableStart+TableContent+TableEnd;

    for (var i=0;i<AvailableAtSlot.length;i++) {
        Red = Green = Blue = Math.round(255/2);
        if (MinAvailable!=MaxAvailable) {
            Red = Math.round(((204)*(MaxAvailable-AvailableAtSlot[i].length)/(MaxAvailable-MinAvailable))+51);
            Green = Math.round(((102)*(MaxAvailable-AvailableAtSlot[i].length)/(MaxAvailable-MinAvailable))+153);
            Blue = Math.round(((255)*(MaxAvailable-AvailableAtSlot[i].length)/(MaxAvailable-MinAvailable))+0);
        }
        document.getElementById("GroupTime"+TimeOfSlot[i]).style.background="#"+(Red*256*256+Green*256+Blue).toString(16);
    }
}

function ShowSlot(Time) {
    e = document.getElementById("ParticipantTimeZone");
    timezone = e[e.selectedIndex].value;

    if (isNaN(Time)||(Time==0)) {
        document.getElementById("AvailableDate").innerHTML = "&nbsp;";
        document.getElementById("Available").innerHTML = "";
        document.getElementById("Unavailable").innerHTML = "";
        document.getElementById("AvailableFraction").innerHTML = "0/"+AvailableIDs.length;
        return;
    }

    AvailableList = "";
    UnavailableList = "";
    AvailableCount=0;
    Count=0;
    for (var i=0;i<AvailableIDs.length;i++) {
        Count++;
        var found = (-1 != AvailableAtSlot[TimeOfSlot.indexOf(Time)].indexOf(AvailableIDs[i]) );
        if (found) {
            AvailableList += PeopleNames[PeopleIDs.indexOf(AvailableIDs[i])]+"<br>";
            AvailableCount++;
        } else
            UnavailableList += PeopleNames[PeopleIDs.indexOf(AvailableIDs[i])]+"<br>";
    }
    document.getElementById("Available").innerHTML = AvailableList;
    document.getElementById("Unavailable").innerHTML = UnavailableList;
    document.getElementById("AvailableFraction").innerHTML = AvailableCount+"/"+Count;

    var date = new Date(Time*1000);

    var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var monthName = monthNames[moment.tz(date,timezone).format('M')-1];

    var dayOfWeekNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

    var dayOfWeekName = dayOfWeekNames[moment.tz(date,timezone).format('e')];

    var dayOfMonth = moment.tz(date,timezone).format('D');

    var hours = moment.tz(date,timezone).format('H');
    var suffix = "am"; if (hours>=12) suffix="pm";
    if (hours>12) hours=hours-12;
    if (hours==0) hours=12;
    var minutes = "0" + date.getUTCMinutes();
    var year = moment.tz(date,timezone).format('YYYY');
    var timeString = hours + ':' + minutes.substr(-2) + " " + suffix ;

    document.getElementById("AvailableDate").innerHTML = timeString + " on " + dayOfWeekName
            + ", " + monthName + ' ' + dayOfMonth + ", " + year;
    document.getElementById("SlotAvailability").style.display='';
    document.getElementById("LeftPanel").style.display='none';
}

function ShowSlotByTouch(event) {
    if (event.touches.length==1) {
        var touch = event.touches[0];
        var elem = document.elementFromPoint(touch.clientX, touch.clientY);
        var Time = Number(elem.id.replace('GroupTime', ''));
        ShowSlot(Time);
    }
}

function ShowSlotByTouchMove(event) {
    if (event.touches.length==1) {
        event.preventDefault();
    }
    ShowSlotByTouch(event);
}

function RestoreLeftSide(event) {
    document.getElementById("SlotAvailability").style.display='none';
    document.getElementById("LeftPanel").style.display='';
}
// 用户   登录 
function ProcessLogin() {

    var parameters = "id=<c:out value='${ eventID}'></c:out>"
            +"&name="+document.getElementById('name').value
            +"&password="+document.getElementById('password').value;
    new Ajax.Request("ProcessLogin", {
        method: "post",
        parameters: "id=<c:out value='${ eventID}'></c:out>"
                +"&name="+document.getElementById('name').value
                +"&password="+document.getElementById('password').value,
        asynchronous:true,
        onSuccess:function(t) {
            PersonID = parseInt(t.responseText);
            if (PersonID) {
                UserID = PersonID;
                ReColorIndividual();
                document.getElementById("SignIn").style.display='none';
                document.getElementById("YouGrid").style.display='';
                if (-1==PeopleIDs.indexOf(UserID)) {
                    PeopleIDs.push(UserID);
                    PeopleNames.push(document.getElementById('name').value);
                }
                document.getElementById("UserName").innerHTML=PeopleNames[PeopleIDs.indexOf(UserID)];
            } else {
                alert(t.responseText);
            }
            //alert(PeopleIDs[0]);
        }
    });
    return false;
}
// 选择 一个   ok
function SelectFromHere(e) {
    timeOfSlot = Number(e.target.getAttribute("data-time"));
    slotID = TimeOfSlot.indexOf(timeOfSlot);
    ChangeToAvailable = (-1 == AvailableAtSlot[slotID].indexOf(UserID) );
    IsMouseDown=true;

    ToCol = Number(e.target.getAttribute("data-col"));
    ToRow = Number(e.target.getAttribute("data-row"));
    FromCol = ToCol;
    FromRow = ToRow;
    ReColorIndividual();
}


function SelectToHere(e) {
    if (IsMouseDown) {
        ToCol = Number(e.target.getAttribute("data-col"));
        ToRow = Number(e.target.getAttribute("data-row"));
        ReColorIndividual();
    }
}

function SelectFromHereByTouch(event) {
    if (event.touches.length==1) {
//      event.preventDefault();
        //     var touch = event.touches[0];
        //     var elem = document.elementFromPoint(touch.clientX, touch.clientY);
        //     var Time = Number(elem.id.replace('YouTime', ''));
        SelectFromHere(event);
    } else {
        return true;
    }
}

function SelectToHereByTouch(event) {
    if (event.touches.length==1) {
        event.preventDefault();
        var touch = event.touches[0];
        var elem = document.elementFromPoint(touch.clientX, touch.clientY);
        if (elem.hasAttribute("data-col")) {
            ToCol = Number(elem.getAttribute("data-col"));
            ToRow = Number(elem.getAttribute("data-row"));
            ReColorIndividual();
        }
    } else {
        return true;
    }
}

function SelectStopByTouch(event) {
    SelectStop();
}
//选定时
function SelectStop() {
    if (!IsMouseDown) return;
    var TimesToToggle = new Array();
    var binaryAvailability = "";
    for (var i=0;i<TimeOfSlot.length;i++) {
        if (FromCol<ToCol) { ColA=FromCol; ColB=ToCol; } else { ColA=ToCol; ColB=FromCol; }
        if (FromRow<ToRow) { RowA=FromRow; RowB=ToRow; } else { RowA=ToRow; RowB=FromRow; }

        var currentElement = document.getElementById("YouTime"+TimeOfSlot[i]);
        var dataCol = Number(currentElement.getAttribute("data-col"));
        var dataRow = Number(currentElement.getAttribute("data-row"));

        var WithinX = ((ColA<=dataCol)&&(dataCol<=ColB));
        var WithinY = ((RowA<=dataRow)&&(dataRow<=RowB));

        if (WithinX && WithinY) {
            TimesToToggle.push(TimeOfSlot[i]);
            if (ChangeToAvailable && (-1==AvailableAtSlot[i].indexOf(UserID)))
                AvailableAtSlot[i].push(UserID);
            if ((!ChangeToAvailable) && (-1!=AvailableAtSlot[i].indexOf(UserID))) {
                SplitSpot = AvailableAtSlot[i].indexOf(UserID);
                AvailableAtSlot[i].splice(SplitSpot,1);
            }
        }
        if (-1!=AvailableAtSlot[i].indexOf(UserID)) {
            binaryAvailability += "1";
        } else {
            binaryAvailability += "0";
        }
    }

    new Ajax.Request("SavaTimes", {
        method: "post",
        parameters: "person="+UserID
        		+"&adates="+adates
                +"&event=<c:out value='${ eventID}'></c:out>"
                +"&slots="+TimesToToggle.join(",")
                +"&availability="+binaryAvailability
                +"&ChangeToAvailable="+ChangeToAvailable,
        asynchronous:true,
        onSuccess:function(t) {
            //   alert(t.responseText);
        }
    });

    IsMouseDown=false;
    FromCol=-1; ToCol=-1; FromRow=-1; ToRow=-1;
    ReColorIndividual();
    ReColorGroup();
}

var AvailableIDs=new Array();

</script>

<div id="NewEventNameDiv" style="padding:20px 0px 20px 20px;font-size:30px;">
<!-- 用于输出 表达式 到 浏览器 -->
	<c:out value="${ NewEventName}"></c:out><br>
	<div style="font-size: 12px;padding-top:12px;">
		<div id="ChooseTimeZone" style="padding-top:12px; height:12px;">
			<span id="YourTimeZone">&nbsp;</span>
			<select name="ParticipantTimeZone" id="ParticipantTimeZone" onchange="LoadAvailabilityGrids();" style="display:none;"></select><br>
		</div>
	</div> 
</div>

<!-- 处理 数据 -->
<script type="text/javascript">
	// 时间 天为 单位
	var adates = "";
	// 初始化 数组
	var index = 0;
	var bigSize = 0;
	<c:forEach var="s" items="${ smalltime }">
		TimeOfSlot[index] = Number("${s.getStartT()}");
		AvailableAtSlot[index]=new Array();
		index++;
	    //document.writeln(TimeOfSlot[index]);
	</c:forEach>
	var indexofuser = 0;
	<c:forEach var="s" items="${ bigtime }">
		adates = adates + "${ s.getDate() },"
		bigSize++;
	</c:forEach>
	<c:forEach var="s" items="${ users }">
		PeopleNames[indexofuser] = "${ s.getUserName() }";
		PeopleIDs[indexofuser] = ${ s.getUserID() };
		AvailableIDs.push(${ s.getUserID() });
		indexofuser++;
	</c:forEach>
	<c:forEach var="s" items="${ alss }">
		AvailableAtSlot[${ s.getIndex() }].push(${ s.getUserID() });
	</c:forEach>
	
	// 起始时间 终止时间
	var startT = "${param.NoEarlierThan}";
	var endT = "${param.NoLaterThan}";
	startT = parseInt(startT);
	endT = parseInt(endT);
</script>

<!-- 界面 -->
<div id="AvailabilityGrids">

    <div onselectstart="return false;" style="width:50%;display:inline-block;*display:inline;zoom:1; white-space:nowrap;overflow:auto; vertical-align:top; text-align:center;">
        <div class="HalfPanel">
        	<!-- 大家的选择  -->
        	<div id="SlotAvailability" style="display:none;">
		      <div class='GridTitle'>
		        <div id="AvailableFraction" style="display:inline-block;*display:inline;zoom:1;"></div>&nbsp;Available        <div class='GridSubtitle'>
		          <div id="AvailableDate"></div>
		          <table>
		          <tr><td width=200 align=center><b><u>Available</u></b></td><td width=200 align=center><b><u>Unavailable</u></b></td></tr>
		          <tr><td valign=top align=center><div id="Available"></td><td valign=top align=center><div id="Unavailable"></td></tr>
		          </table>
		        </div>
		      </div>
		    </div>
            <div id="YouAvailability">
                <div id="LeftPanel">
                
                    <div id="SignIn">
                        <div class="GridTitle">Sign In<br>
                            <div class="GridSubtitle">
                                <table>
                                    <tbody><tr><td>Your Name:</td><td align="left"><input style="width:150px" id="name"></td></tr>
                                    <tr><td>Password (optional):</td><td align="left"><input style="width:150px" id="password" type="password"></td></tr>
                                    </tbody></table>
                                <input type="button" value="Sign In" onclick="ProcessLogin()"><br><br>
                                Name/Password are only for this event.<br>
                                New to this event? Make up a password.<br>
                                Returning? Use the same name/password.          </div>
                        </div>
                    </div>
                    
                    
                    <div id="YouGrid" style="display:none;">
                    
                    
                        <div class="GridTitle" style="display:inline-block;*display:inline;zoom:1; margin-left:40px;">
                            <span id="UserName"></span>'s Availability<div class="GridSubtitle">    <div style="padding:5px;" onselectstart="return true;">
                            Unavailable<div style="display:inline-block;*display:inline;zoom:1;vertical-align:middle;padding:0px 5px 0px 5px;">
                            <table width="30" height="10" cellpadding="0" cellspacing="0" style="border: solid 1px black"><tbody><tr>
                                <td bgcolor="#ffdede">&nbsp;</td></tr></tbody></table></div>
                            Available<div style="display:inline-block;*display:inline;zoom:1;vertical-align:middle;padding:0px 5px 0px 5px;">
                            <table width="30" height="10" cellpadding="0" cellspacing="0" style="border: solid 1px black"><tbody><tr>
                                <td bgcolor="#339900">&nbsp;</td></tr></tbody></table></div>
                        </div>

                            Click and Drag to Toggle; Saved Immediately</div>
                        </div><br clear="all">
						
				<div style="display:inline-block;*display:inline;zoom:1; vertical-align:bottom;text-align:right;">
				<!-- 生成 左边时间 轴 -->
					<c:forEach var = "s" items = "${hours }" varStatus="status">
						<c:forEach var = "in" begin = "1" end = "4">
						<div style="font-size:0px;vertical-align:top;">
							<div style="vertical-align:top;display:inline-block;*display:inline;zoom:1;width:44px;height:9px;font-size:0px;border-left: 1px white solid;margin-top: 1px;border-right: 1px white solid;">
							<c:if test="${in % 4 == 0 }">
								<div style="text-align:right;width:44px;font-size:10px;margin:4px 4px 0px 0px;"><c:out value="${s }"></c:out>&nbsp;</div>
							</c:if>
							</div>
						</div>
						</c:forEach>
					</c:forEach>
                </div>
                <div style="display:inline-block;*display:inline;zoom:1;vertical-align:bottom;text-align:right;">
					<c:forEach var="s" items="${bigtime }">
						<div style="display:inline-block;*display:inline;zoom:1;text-align:center;font-size:10px;width:44px;padding-right:1px;"><c:out value="${s.getDate() }"></c:out><br>
						<div style="display:inline-block;*display:inline;zoom:1;font-size:16px;">Fri</div></div>
					</c:forEach>
					
				<div id="YouGridSlots" class="GroupGrid" style="background-color:white; display:inline">
					<c:set var = "i" value = "-1"></c:set>
                	<c:forEach begin = "1" end = "${smalltimecount }" step = "1">
	                	<c:set var = "i" value = "${i+1 }"></c:set>
	                	<c:set var = "j" value = "-1"></c:set>
	                	<div style="font-size:0px;vertical-align:top;">
	                		<c:forEach  begin = "1" end = "${bigtimecount }" step = "1">
	                		<c:set var = "j" value = "${j+1 }"></c:set>
	                		<div id="YouTime${smalltime[j * smalltimecount + i].getStartT() }" data-col="${j }" data-row="${i }" data-time="${smalltime[j * smalltimecount + i].getStartT() }" onmousedown="SelectFromHere(event);" onmouseover="SelectToHere(event);" ontouchstart="SelectFromHereByTouch(event);" ontouchmove="SelectToHereByTouch(event);" ontouchend="SelectStopByTouch(event);" style="vertical-align:top;display:inline-block;*display:inline;zoom:1;width:44px;height:9px;font-size:0px;border-left: 1px black solid;background: #ffdede;border-top: 1px black solid;border-right: 1px black solid;"></div>
	                		</c:forEach>
	       				</div>
    				</c:forEach>
                
                </div>

        		</div>

                    
                    </div></div></div>
        </div><br><br>
    </div><div onselectstart="return false;" style="width:50%;display:inline-block;*display:inline;zoom:1; white-space:nowrap;overflow:auto; vertical-align:top; text-align:center;">
    <div class="HalfPanel">
    <div id="GroupAvailability">
        <div id="GroupGrid">
            <div class="GridTitle" style="display:inline-block;*display:inline;zoom:1; margin-left:40px;">
                Group's Availability<div class="GridSubtitle"><div style="padding:5px" ;=""><div id="MinAvailable" style="display:inline-block;*display:inline;zoom:1;">0/0</div>&nbsp;Available<div id="GroupKey" style="display:inline-block;*display:inline;zoom:1;vertical-align:middle;padding:0px 5px 0px 5px;"><table width="100" height="10" cellpadding="0" cellspacing="0" style="border: solid 1px black"><tbody><tr><td bgcolor="#808080">&nbsp;</td></tr></tbody></table></div><div id="MaxAvailable" style="display:inline-block;*display:inline;zoom:1;">0/0</div>&nbsp;Available</div>Mouseover the Calendar to See Who Is Available</div>
       		</div><br clear="all">
       		
				<div style="display:inline-block;*display:inline;zoom:1; vertical-align:bottom;text-align:right;">
				<!-- 生成 左边时间 轴 -->
					<c:forEach var = "s" items = "${hours }" varStatus="status">
						<c:forEach var = "in" begin = "1" end = "4">
						<div style="font-size:0px;vertical-align:top;">
							<div style="vertical-align:top;display:inline-block;*display:inline;zoom:1;width:44px;height:9px;font-size:0px;border-left: 1px white solid;margin-top: 1px;border-right: 1px white solid;">
							<c:if test="${in % 4 == 0 }">
								<div style="text-align:right;width:44px;font-size:10px;margin:4px 4px 0px 0px;"><c:out value="${s }"></c:out>&nbsp;</div>
							</c:if>
							</div>
						</div>
						</c:forEach>
					</c:forEach>
                </div>
                <div style="display:inline-block;*display:inline;zoom:1;vertical-align:bottom;text-align:right;">
					<c:forEach var="s" items="${bigtime }">
						<div style="display:inline-block;*display:inline;zoom:1;text-align:center;font-size:10px;width:44px;padding-right:1px;"><c:out value="${s.getDate() }"></c:out><br>
						<div style="display:inline-block;*display:inline;zoom:1;font-size:16px;">Fri</div></div>
					</c:forEach>
					
				<div id="GroupGridSlots" class="GroupGrid" style="background-color:white; display:inline">
					<c:set var = "i" value = "-1"></c:set>
                	<c:forEach begin = "1" end = "${smalltimecount }" step = "1">
	                	<c:set var = "i" value = "${i+1 }"></c:set>
	                	<c:set var = "j" value = "-1"></c:set>
	                	<div style="font-size:0px;vertical-align:top;">
	                		<c:forEach  begin = "1" end = "${bigtimecount }" step = "1">
	                		<c:set var = "j" value = "${j+1 }"></c:set>
	                		<div id="GroupTime${smalltime[j * smalltimecount + i].getStartT() }" data-col="${j }" data-row="${i }" data-time="${smalltime[j * smalltimecount + i].getStartT() }" onmouseover="ShowSlot(${smalltime[j * smalltimecount + i].getStartT() });" onmouseout="RestoreLeftSide(event);" ontouchstart="ShowSlotByTouch(event);" ontouchmove="ShowSlotByTouchMove(event);" ontouchend="RestoreLeftSide(event);" style="vertical-align:top;display:inline-block;*display:inline;zoom:1;width:44px;height:9px;font-size:0px;border-left: 1px black solid;background: #808080;border-top: 1px black solid;"></div>
	       					</c:forEach>
	       				</div>
    				</c:forEach>
                
                </div>

        </div></div></div></div>
</div>


<script>
	ReColorGroup();
    jQuery("div.GroupGrid").mouseleave(function() {
        document.getElementById("SlotAvailability").style.display='none';
        document.getElementById("LeftPanel").style.display='';
    });
</script>
<br>
</div>

</body>
</html>
