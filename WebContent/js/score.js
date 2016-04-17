	function loadAutoSumScoreData(columnName){

		$("#list").children("tbody").empty();
		var path = "http://ci.stormbots.com:8080/CITools2015/rest/score/sum_auto";
		var teamNumber = document.getElementById('textBox').value;
		var selected = $("#sorter option:selected").attr("value");
		
		if(columnName != "" && columnName != null && columnName != "SORT" && columnName == "TEAM_NUMBER" && teamNumber == ""){
			path += "/sort/"+columnName;
		}
		
		if(teamNumber != null && teamNumber != "" && columnName != "SORT"){
			path += "/"+teamNumber;
		}
		
		if(columnName == "SORT"){
			path += "/sort/"+selected;
		}	
		
		$.ajax({
			type: "GET",
			url: path,
			dataType: "json",
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					var element = $("<tr id=\"data\"></tr>");
					$("#list").append(element);
					
					console.log(data[i]);
					
					$(element).last().append("<td>" + data[i].teamNumber + "</td>");
					$(element).last().append("<td>" + data[i].pickUpYellow + "</td>");
					$(element).last().append("<td>" + data[i].failYellow + "</td>");
					$(element).last().append("<td>" + data[i].stackYellow + "</td>");
					$(element).last().append("<td>" + data[i].pickUpContainer + "</td>");
					$(element).last().append("<td>" + data[i].failContainer + "</td>");
					$(element).last().append("<td>" + data[i].stackContainer + "</td>");
					$(element).last().append("<td>" + data[i].autoZone + "</td>");
				}
			}
		});
					
	}
	
	function loadAutoAverageScoreData(columnName){

		$("#list2").children("tbody").empty();
		var path = "http://ci.stormbots.com:8080/CITools2015/rest/score/average_auto";
		var teamNumber = document.getElementById('textBox2').value;
		var selected = $("#sorter2 option:selected").attr("value");
		
		if(columnName != "" && columnName != null && columnName != "SORT" && columnName == "TEAM_NUMBER" && teamNumber == ""){
			path += "/sort/"+columnName;
		}
		
		if(teamNumber != null && teamNumber != "" && columnName != "SORT"){
			path += "/"+teamNumber;
		}
		
		if(columnName == "SORT"){
			path += "/sort/"+selected;
		}	
		
		$.ajax({
			type: "GET",
			url: path,
			dataType: "json",
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					var element = $("<tr id=\"data\"></tr>");
					$("#list2").append(element);
					
					console.log(data[i]);
					
					$(element).last().append("<td>" + data[i].teamNumber + "</td>");
					$(element).last().append("<td>" + (data[i].pickUpYellow * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].failYellow * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].stackYellow * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].pickUpContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].failContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].stackContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].autoZone * 100) + "%</td>");
				}
			}
		});
					
	}
	
	function loadTeleSumScoreData(columnName){

		$("#list3").children("tbody").empty();
		var path = "http://ci.stormbots.com:8080/CITools2015/rest/score/sum_tele";
		var teamNumber = document.getElementById('textBox3').value;
		var selected = $("#sorter3 option:selected").attr("value");
		
		if(columnName != "" && columnName != null && columnName != "SORT" && columnName == "TEAM_NUMBER" && teamNumber == ""){
			path += "/sort/"+columnName;
		}
		
		if(teamNumber != null && teamNumber != "" && columnName != "SORT"){
			path += "/"+teamNumber;
		}
		
		if(columnName == "SORT"){
			path += "/sort/"+selected;
		}	
		
		$.ajax({
			type: "GET",
			url: path,
			dataType: "json",
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					var element = $("<tr id=\"data\"></tr>");
					$("#list3").append(element);
					
					console.log(data[i]);
					
					$(element).last().append("<td>" + data[i].teamNumber + "</td>");
					$(element).last().append("<td>" + data[i].pickUpGray + "</td>");
					$(element).last().append("<td>" + data[i].failGray + "</td>");
					$(element).last().append("<td>" + data[i].stackGray + "</td>");
					$(element).last().append("<td>" + data[i].pickUpContainer + "</td>");
					$(element).last().append("<td>" + data[i].failContainer + "</td>");
					$(element).last().append("<td>" + data[i].stackContainer + "</td>");
					$(element).last().append("<td>" + data[i].pickUpLitter + "</td>");
					$(element).last().append("<td>" + data[i].failLitter + "</td>");
					$(element).last().append("<td>" + data[i].scoreLitter + "</td>");
				}
			}
		});
					
	}
	
	function loadTeleAverageScoreData(columnName){

		$("#list4").children("tbody").empty();
		var path = "http://ci.stormbots.com:8080/CITools2015/rest/score/average_tele";
		var teamNumber = document.getElementById('textBox4').value;
		var selected = $("#sorter4 option:selected").attr("value");
		
		if(columnName != "" && columnName != null && columnName != "SORT" && columnName == "TEAM_NUMBER" && teamNumber == ""){
			path += "/sort/"+columnName;
		}
		
		if(teamNumber != null && teamNumber != "" && columnName != "SORT"){
			path += "/"+teamNumber;
		}
		
		if(columnName == "SORT"){
			path += "/sort/"+selected;
		}	
		
		$.ajax({
			type: "GET",
			url: path,
			dataType: "json",
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					var element = $("<tr id=\"data\"></tr>");
					$("#list4").append(element);
					
					console.log(data[i]);
					
					$(element).last().append("<td>" + data[i].teamNumber + "</td>");
					$(element).last().append("<td>" + (data[i].pickUpGray * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].failGray * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].stackGray * 1)+ "</td>");
					$(element).last().append("<td>" + (data[i].pickUpContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].failContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].stackContainer * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].pickUpLitter * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].failLitter * 1) + "</td>");
					$(element).last().append("<td>" + (data[i].scoreLitter * 1) + "</td>");
				}
			}
		});
					
	}