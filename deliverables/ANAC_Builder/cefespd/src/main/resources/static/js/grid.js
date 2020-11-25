var grid = {};

var defaultAlign = 'left';
var defaultWidth = 100;

$.fn.extend({
	grid: function(parameters) {
		var id = $(this).attr('id');
		grid[id] = parameters;
		if(grid[id].request == null) {
			grid[id].request = {start: 0, dir: 'ASC'};
		}
		grid[id].requestDefault = Object.assign({}, grid[id].request);
		gridInitialize(id);
	}
})

function gridInitialize(id) {
	if(grid[id].autoResize) {
		$('#' + id).empty().append('<table class="grid" style="width: 100%;"><thead></thead><tbody></tbody><tfoot></tfoot></table>');
	} else {
		$('#' + id).empty().append('<table class="grid"><thead></thead><tbody></tbody><tfoot></tfoot></table>');
	}
	var tbody = '';
	for(var cont = 0 ; cont < (grid[id].request.limit) ; cont++) {
		tbody = tbody + '<tr>';
		$.each(grid[id].columns, function() {
			tbody = tbody + '<td></td>';
		});
		tbody = tbody + '</tr>';
	}
	$('#' + id + ' table tbody').append(tbody);
	if(grid[id].footer == null || grid[id].footer) {
		$('#' + id + ' table tfoot').append('<tr><td colspan="' + grid[id].columns.length + '"></td></tr>');
	}
	gridHead(id);
}

function gridHead(id) {
	var thead = '<tr>';
	var search = false;
	$.each(grid[id].columns, function(idColumn, column) {
		if(column.name == null) {
			column.name = '';
		}
		if(column.width == null) {
			column.width = defaultWidth;
		}
		if(column.sortable) {
			thead = thead + '<th class="text-center sort" id="' + column.id + '" onclick="gridSort(\'' + id + '\', this);" style="min-width: ' + column.width + 'px;">' + column.name + '</th>';
		} else {
			thead = thead + '<th class="text-center" id="' + column.id + '" style="min-width: ' + column.width + 'px;">' + column.name + '</th>';
		}
		if(column.search != null) {
			search = true;
		}
	})
	thead = thead + '</tr>';
	if(search) {
		thead = thead + '<tr>';
		$.each(grid[id].columns, function(idColumn, column) {
			if(column.search == 'text') {
				thead = thead + '<td><input id="' + column.id + '" onkeyup="gridFirst(\'' + id + '\');" oninput="gridFirst(\'' + id + '\');" style="width: 100%;" type="text" value=""></td>';
			} else if(column.search == 'listBox') {
				thead = thead + '<td><select id="' + column.id + '" onchange="gridFirst(\'' + id + '\');" style="width: 100%;"></select></td>';
				$.ajax({
					type: 'get',
					url: column.searchUrl
				}).done(function(data) {
					if(data.success) {
						var select = '';
						if(column.searchAll) {
							select = select + '<option></option>';
						}
						$.each(data.result, function(idOption, option) {
							select = select + '<option value="' + option.value + '">' + option.text + '</option>';
						})
						$('#' + id + ' table thead tr td select[id="' + column.id + '"]').append(select);
					} else {
						var message;
						if(data.message != null) {
							message = '<p>' + data.message + '</p>';
						} else {
							message = '<p>Errore !</p>';
						}
						$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
					}
				}).fail(function(jqXHR, textStatus) {
					var message = '<p>Errore : ' + jqXHR.status + ' </p>';
					$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
				})
			} else if(column.search == 'listBoxLocal') {
				var result = window[column.id + 'ListBoxLocal_' + id]();
				var select = '';
				if(column.searchAll) {
					select = select + '<option></option>';
				}
				$.each(result, function(value, text) {
					select = select + '<option value="' + value + '">' + text + '</option>';
				})
				thead = thead + '<td><select id="' + column.id + '" onchange="gridFirst(\'' + id + '\');" style="width: 100%;">' + select + '</select></td>';
			} else {
				thead = thead + '<td></td>';
			}
		})
		thead = thead + '</tr>';
	}
	$('#' + id + ' table thead').append(thead);
	if(grid[id].request.dir == 'ASC') {
		$('#' + id + ' table thead tr th[id="' + grid[id].request.sort + '"]').addClass('sortAsc');
	} else {
		$('#' + id + ' table thead tr th[id="' + grid[id].request.sort + '"]').addClass('sortDesc');
	}
	if(grid[id].autoLoad == null || grid[id].autoLoad == true) {
		gridBody(id);
	}
}

function gridBody(id) {
	var searchMap = {};
	$.each(grid[id].columns, function(idColumn, column) {
		if(column.search == 'text' && $('#' + id + ' table thead tr td input[id="' + column.id + '"]').val() != '') {
			searchMap[column.id] = $('#' + id + ' table thead tr td input[id="' + column.id + '"]').val();
		} else if((column.search == 'listBox' || column.search == 'listBoxLocal') && $('#' + id + ' table thead tr td select[id="' + column.id + '"]').val() != '') {
			searchMap[column.id] = $('#' + id + ' table thead tr td select[id="' + column.id + '"]').val();
		}
	})
	grid[id].request.searchMap = searchMap;
	$.ajax({
		contentType: "application/json",
		data: JSON.stringify(grid[id].request),
		type: 'post',
		url: grid[id].url
	}).done(function(data) {
		if(data.success) {
			var tbody = '';
			$.each(data.result, function(idRecord, record) {
				tbody = tbody + '<tr>';
				$.each(grid[id].columns, function(idColumn, column) {
					var found = false;
					if(column.align == null) {
						column.align = defaultAlign;
					}
					$.each(record, function(idField, field) {
						if(idField == column.id) {
							if(column.executeFunction != null) {
								tbody = tbody + '<td align="' + column.align + '" class="hover" style="max-width: ' + column.width + 'px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">' + window[column.executeFunction](record, column.id) + '</td>';
							} else {
								tbody = tbody + '<td align="' + column.align + '" class="hover" style="max-width: ' + column.width + 'px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="' + String(field).replace(/"/g,  "\\\"").replace(/'/g, "&#039;") + '">' + field + '</td>';
							}
							found = true;
						}
					})
					if(!found) {
						if(column.executeFunction != null) {
							tbody = tbody + '<td align="' + column.align + '" class="hover">' + window[column.executeFunction](record, null) + '</td>';
						} else {
							tbody = tbody + '<td class="hover"></td>';
						}
					}
				})
				tbody = tbody + '</tr>';
			})
			for(var cont = 0 ; cont < (grid[id].request.limit - data.result.length) ; cont++) {
				tbody = tbody + '<tr>';
				$.each(grid[id].columns, function() {
					tbody = tbody + '<td></td>';
				});
				tbody = tbody + '</tr>';
			}
			$('#' + id + ' table tbody').empty().append(tbody);
			if(grid[id].footer == null || grid[id].footer) {
				gridFoot(id, data.totalCount, data.result.length);
			}
		} else {
			var message;
			if(data.message != null) {
				message = '<p>' + data.message + '</p>';
			} else {
				message = '<p>Errore !</p>';
			}
			$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
		}
	}).fail(function(jqXHR, textStatus) {
		var message = '<p>Errore : ' + jqXHR.status + ' </p>';
		$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
	})
}

function gridFoot(id, totalCount, record) {
	var tfoot = '<tr><td colspan="' + grid[id].columns.length + '">';
	if(totalCount > 0) {
		tfoot = tfoot + '<div style="float: left;">';
		if(grid[id].request.start == 0) {
			tfoot = tfoot + '<button class="firstDisabled"></button>';
			tfoot = tfoot + '<button class="previousDisabled"></button>';
		} else {
			tfoot = tfoot + '<button class="first" onclick="gridFirst(\'' + id + '\')"></button>';
			tfoot = tfoot + '<button class="previous" onclick="gridPrevious(\'' + id + '\')"></button>';
		}
		var page = (grid[id].request.start + grid[id].request.limit) / grid[id].request.limit;
		var totalPage;
		if((totalCount % grid[id].request.limit) == 0) {
			totalPage = totalCount / grid[id].request.limit;
		} else {
			totalPage = (totalCount + grid[id].request.limit - totalCount % grid[id].request.limit) / grid[id].request.limit;
		}
		tfoot = tfoot + '| Pagina ' + page + ' di ' + totalPage + ' |';
		if((grid[id].request.start + record) == totalCount) {
			tfoot = tfoot + '<button class="nextDisabled"></button>';
			tfoot = tfoot + '<button class="lastDisabled"></button>';
		} else {
			tfoot = tfoot + '<button class="next" onclick="gridNext(\'' + id + '\', \'' + totalCount + '\')"></button>';
			tfoot = tfoot + '<button class="last" onclick="gridLast(\'' + id + '\', \'' + totalCount + '\')"></button>';
		}
		tfoot = tfoot + '</div>';
		tfoot = tfoot + '<div style="float: right;">Record da ' + (grid[id].request.start + 1) + ' a ' + (grid[id].request.start + record) + ' di ' + totalCount + '</div>';
	} else {
		tfoot = tfoot + '<div style="float: left;">Non sono presenti record</div>';
	}
	$('#' + id + ' table tfoot').empty().append(tfoot);
}

function gridSort(id, column) {
	if($(column).hasClass('sortAsc')) {
		$('#' + id + ' table thead tr th').removeClass('sortAsc').removeClass('sortDesc');
		$(column).addClass('sortDesc');
		grid[id].request.dir = 'DESC'
	} else {
		$('#' + id + ' table thead tr th').removeClass('sortAsc').removeClass('sortDesc');
		$(column).addClass('sortAsc');
		grid[id].request.dir = 'ASC'
	}
	grid[id].request.start = 0;
	grid[id].request.sort = $(column).attr('id');
	gridBody(id);
}

function gridFirst(id) {
	grid[id].request.start = 0;
	gridBody(id);
}

function gridPrevious(id) {
	grid[id].request.start = grid[id].request.start - grid[id].request.limit;
	gridBody(id);
}

function gridNext(id, totalCount) {
	grid[id].request.start = grid[id].request.start + grid[id].request.limit;
	gridBody(id);
}

function gridLast(id, totalCount) {
	if((totalCount % grid[id].request.limit) == 0) {
		grid[id].request.start = totalCount - grid[id].request.limit;
	} else {
		grid[id].request.start = totalCount - (totalCount % grid[id].request.limit);
	}
	gridBody(id);
}

function gridReset(id) {
	$.each(grid[id].columns, function(idColumn, column) {
		if(column.search == 'text' && $('#' + id + ' table thead tr td input[id="' + column.id + '"]').val() != '') {
			$('#' + id + ' table thead tr td input[id="' + column.id + '"]').val('');
		} else if((column.search == 'listBox' || column.search == 'listBoxLocal') && $('#' + id + ' table thead tr td select[id="' + column.id + '"]').val() != '') {
			$('#' + id + ' table thead tr td select[id="' + column.id + '"] option:first').prop('selected', true);
		}
	})
	var tbody = '';
	for(var cont = 0 ; cont < (grid[id].request.limit) ; cont++) {
		tbody = tbody + '<tr>';
		$.each(grid[id].columns, function() {
			tbody = tbody + '<td></td>';
		})
		tbody = tbody + '</tr>';
	}
	$('#' + id + ' table tbody').empty().append(tbody);
	grid[id].request = Object.assign({}, grid[id].requestDefault);
	$('#' + id + ' table thead tr th').removeClass('sortAsc').removeClass('sortDesc');
	if(grid[id].request.dir == 'ASC') {
		$('#' + id + ' table thead tr th[id="' + grid[id].request.sort + '"]').addClass('sortAsc');
	} else {
		$('#' + id + ' table thead tr th[id="' + grid[id].request.sort + '"]').addClass('sortDesc');
	}
}