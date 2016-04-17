	function loadTeamData(){

		$("#list").children("tbody").empty();
		var path = "http://ci.stormbots.com:8080/CITools2015/rest/team";
		var teamNumber = document.getElementById('textBox').value;
		
		if(teamNumber !== null || teamNumber !== ""){
			path += "/"+teamNumber;
		}
		$.ajax({
			type: "GET",
			url: path,
			dataType: "json",
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					var element = $("<tr id=\"data\"></tr>");
					$("#list").append(element);
					
					$.each(data[i], function(i, val){
						if(i !== "teamNumberInteger" && i !== "teamId"){
							$(element).last().append("<td>" + val + "</td>");
							console.log(i + " : " + val);
						}
					});
					
				}
			}
		});
	}