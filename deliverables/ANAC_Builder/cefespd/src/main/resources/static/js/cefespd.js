var cefecertisUrl;
var cefespdbuilderUrl;
var languages = {};
var nationalEntities = {};
var criteriaEspd = [];

$(function() {
	$.ajax({
		contentType: "application/json",
		type: 'get',
		url: 'cefespdbuilderUrl'
	}).done(function(data) {
		cefespdbuilderUrl = data.result;
	})
	$.ajax({
		contentType: "application/json",
		type: 'get',
		url: 'cefecertisUrl'
	}).done(function(data) {
		cefecertisUrl = data.result;
		$.ajax({
			async: false,
			type: 'get',
			url: cefecertisUrl + 'languagesGrid'
		}).done(function(data) {
			if(data.success) {
				$.each(data.result, function(idOption, option) {
					languages[option.value] = option.text;
				})
			} else {
				var message;
				if(data.message != null) {
					message = '<p>' + data.message + '</p>';
				} else {
					message = '<p>Errore !</p>';
				}
				$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
			}
		}).fail(function (jqXHR, textStatus) {
			var message = '<p>Errore : ' + jqXHR.status + ' </p>';
			$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
		})
		$.ajax({
			async: false,
			type: 'get',
			url: cefecertisUrl + 'nationalEntitiesGrid'
		}).done(function(data) {
			if(data.success) {
				$.each(data.result, function(idOption, option) {
					nationalEntities[option.value] = option.text;
				})
			} else {
				var message;
				if(data.message != null) {
					message = '<p>' + data.message + '</p>';
				} else {
					message = '<p>Errore !</p>';
				}
				$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
			}
		}).fail(function (jqXHR, textStatus) {
			var message = '<p>Errore : ' + jqXHR.status + ' </p>';
			$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
		})
		$('#criteria').grid({
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', search: 'text', sortable: true, width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', executeFunction: 'language_criteria', id: 'language', name: 'Lingua', search: 'listBoxLocal', width: 125},
				{align: 'left', executeFunction: 'nationalEntity_criteria', id: 'nationalEntity', name: 'Nazione', search: 'listBoxLocal', searchAll: true, width: 125},
				{align: 'center', id: 'startDate', name: 'Inizio', sortable: true, width: 90},
				{align: 'center', id: 'endDate', name: 'Fine', sortable: true, width: 90},
				{align: 'left', id: 'name', name: 'Nome', width: 200},
				{align: 'left', id: 'description', name: 'Descrizione', width: 200},
				{align: 'center', executeFunction: 'criteriaChildren_criteria', width: 25},
				{align: 'center', executeFunction: 'criteriaHistory_criteria', width: 25},
				{align: 'center', executeFunction: 'criteriaEvidences_criteria', width: 25}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id',
				dir: 'ASC'
			},
			url: cefecertisUrl + 'criteriaGrid'
		})
		$('#criteriaChildren').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', sortable: true, width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', executeFunction: 'nationalEntity_criteriaChildren', id: 'nationalEntity', name: 'Nazione', width: 125},
				{align: 'center', id: 'startDate', name: 'Inizio', sortable: true, width: 90},
				{align: 'center', id: 'endDate', name: 'Fine', sortable: true, width: 90},
				{align: 'left', id: 'name', name: 'Nome', width: 200},
				{align: 'left', id: 'description', name: 'Descrizione', width: 200}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id',
				dir: 'ASC'
			},
			url: cefecertisUrl + 'criteriaChildrenGrid'
		})
		$('#criteriaHistory').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', executeFunction: 'nationalEntity_criteriaHistory', id: 'nationalEntity', name: 'Nazione', width: 125},
				{align: 'center', id: 'startDate', name: 'Inizio', sortable: true, width: 90},
				{align: 'center', id: 'endDate', name: 'Fine', sortable: true, width: 90},
				{align: 'left', id: 'name', name: 'Nome', width: 200},
				{align: 'left', id: 'description', name: 'Descrizione', width: 200}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'versionId',
				dir: 'DESC'
			},
			url: cefecertisUrl + 'criteriaHistoryGrid'
		})
		$('#criteriaEvidences').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', sortable: true, width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', id: 'typeCode', name: 'Tipo', width: 200},
				{align: 'left', id: 'name', name: 'Nome', width: 200},
				{align: 'left', id: 'description', name: 'Descrizione', width: 200}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id',
				dir: 'ASC'
			},
			url: cefecertisUrl + 'criteriaEvidencesGrid'
		})
		$('#evidences').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', search: 'text', sortable: true, width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', executeFunction: 'language_evidences', id: 'language', name: 'Lingua', search: 'listBoxLocal', width: 125},
				{align: 'left', executeFunction: 'nationalEntity_evidences', id: 'nationalEntity', name: 'Nazione', search: 'listBoxLocal', searchAll: true, width: 125},
				{align: 'left', id: 'typeCode', name: 'Tipo', width: 200},
				{align: 'left', id: 'name', name: 'Nome', width: 200},
				{align: 'left', id: 'description', name: 'Descrizione', width: 200}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id',
				dir: 'ASC'
			},
			url: cefecertisUrl + 'evidencesGrid'
		})
		$('#synchronizes').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'center', id: 'startDate', name: 'Inizio', width: 150},
				{align: 'center', executeFunction: 'endDate_synchronizes', id: 'endDate', name: 'Fine', width: 150},
				{align: 'center', executeFunction: 'success_synchronizes', id: 'success', width: 25},
				{align: 'center', id: 'criteriaFound', name: 'Criteri trovati', width: 150},
				{align: 'center', id: 'criteriaCreated', name: 'Criteri creati', width: 150},
				{align: 'center', id: 'criteriaUpdated', name: 'Criteri aggiornati', width: 150},
				{align: 'center', executeFunction: 'criteriaTotal_synchronizes', name: 'Criteri totale', width: 150},
				{align: 'center', id: 'criteriaDeleted', name: 'Criteri cancellati', width: 150},
				{align: 'center', id: 'criteriaError', name: 'Errori', width: 150}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'startDate'
			},
			url: cefecertisUrl + 'synchronizesGrid'
		})
		$('#criteriaTaxonomy').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', search: 'text', sortable: true, width: 250},
				{align: 'center', executeFunction: 'criterionFound_criteriaTaxonomy', id: 'criterionFound', width: 25},
				{align: 'left', executeFunction: 'nationalEntity_criteriaTaxonomy', id: 'nationalEntity', name: 'Nazione', search: 'listBoxLocal', searchAll: true, width: 125},
				{align: 'left', id: 'name', name: 'Nome', width: 400},
				{align: 'left', id: 'description', name: 'Descrizione', width: 400}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id',
				dir: 'ASC'
			},
			url: cefecertisUrl + 'criteriaTaxonomyGrid'
		})
		$('#criteriaEspd').grid({
			autoLoad: false,
			autoResize: true,
			columns: [
				{align: 'left', id: 'id', name: 'ID', search: 'text', sortable: true, width: 250},
				{align: 'center', id: 'versionId', name: 'Versione', sortable: true, width: 90},
				{align: 'left', id: 'name', name: 'Nome', width: 400},
				{align: 'left', id: 'description', name: 'Descrizione', width: 400},
				{align: 'center', executeFunction: 'criteriaEspd_check', width: 25}
			],
			request: {
				start: 0,
				limit: 10,
				sort: 'id'
			},
			url: cefecertisUrl + 'criteriaEspdGrid'
		})
	})
})

function show(content, grid) {
	$('.content').hide();
	if(grid != null) {
		gridReset(grid);
		gridBody(grid);
	}
	$('#' + content).show();
}

//--- criteria ---//
function languageListBoxLocal_criteria() {
	return languages;
}

function nationalEntityListBoxLocal_criteria() {
	return nationalEntities;
}

function language_criteria(record) {
	return languages[record.language];
}

function nationalEntity_criteria(record) {
	return nationalEntities[record.nationalEntity];
}

function criteriaChildren_criteria(record) {
	if(record.nationalEntity == 'eu') {
		return '<img onclick=\'criteriaChildrenExecute_criteria("' + record.id + '", "' + record.versionId + '", "' + record.language + '")\' src=\'./images/children.png\' style=\'cursor: pointer;\' title=\'Criteri Figli\'>';
	} else {
		return '';
	}
}

function criteriaChildrenExecute_criteria(parentId, parentVersionId, language) {
	$('#criteriaChildrenLabel').empty().append(' - ' + parentId + ' - ' + parentVersionId);
	gridReset('criteriaChildren');
	grid['criteriaChildren'].request.parameterMap = {parentId: parentId, parentVersionId: parentVersionId, language: language};
	gridBody('criteriaChildren');
	$('#criteriaChildrenPopup').show();
}

function criteriaHistory_criteria(record) {
	if(record.history) {
		return '<img onclick=\'criteriaHistoryExecute_criteria("' + record.id + '", "' + record.language + '")\' src=\'./images/history.png\' style=\'cursor: pointer;\' title=\'History Criterio\'>';
	} else {
		return '';
	}
}

function criteriaHistoryExecute_criteria(id, language) {
	$('#criteriaHistoryLabel').empty().append(' - ' + id);
	gridReset('criteriaHistory');
	grid['criteriaHistory'].request.parameterMap = {id: id, language: language};
	gridBody('criteriaHistory');
	$('#criteriaHistoryPopup').show();
}

function criteriaEvidences_criteria(record) {
	if(record.evidencesNumber > 0) {
		return '<img onclick=\'criteriaEvidencesExecute_criteria("' + record.id + '", "' + record.versionId + '", "' + record.language + '")\' src=\'./images/evidences.png\' style=\'cursor: pointer;\' title=\'Evidenze Criterio (' + record.evidencesNumber + ')\'>';
	} else {
		return '';
	}
}

function criteriaEvidencesExecute_criteria(criterionId, criterionVersionId, language) {
	$('#criteriaEvidencesLabel').empty().append(' - ' + criterionId + ' - ' + criterionVersionId);
	gridReset('criteriaEvidences');
	grid['criteriaEvidences'].request.parameterMap = {criterionId: criterionId, criterionVersionId: criterionVersionId, language: language};
	gridBody('criteriaEvidences');
	$('#criteriaEvidencesPopup').show();
}
//--- criteria ---//

//--- criteriaChildren ---//
function nationalEntity_criteriaChildren(record) {
	return nationalEntities[record.nationalEntity];
}

function criteriaChildrenClose() {
	$('#criteriaChildrenPopup').hide();
}
//--- criteriaChildren ---//

//--- criteriaHistory ---//
function nationalEntity_criteriaHistory(record) {
	return nationalEntities[record.nationalEntity];
}

function criteriaHistoryClose() {
	$('#criteriaHistoryPopup').hide();
}
//--- criteriaHistory ---//

//--- criteriaEvidences ---//
function criteriaEvidencesClose() {
	$('#criteriaEvidencesPopup').hide();
}
//--- criteriaEvidences ---//

// --- evidences --- //
function languageListBoxLocal_evidences() {
	return languages;
}

function nationalEntityListBoxLocal_evidences() {
	return nationalEntities;
}

function language_evidences(record) {
	return languages[record.language];
}

function nationalEntity_evidences(record) {
	return nationalEntities[record.nationalEntity];
}
// --- evidences --- //

//--- synchronizes --- //
function endDate_synchronizes(record) {
	if(record.endDate == null) {
		$('#synchronize').hide();
		setTimeout(function() {
			gridBody('synchronizes');
		}, 1000);
		return 'sincronizzazione ...';
	} else {
		return record.endDate;
	}
}

function success_synchronizes(record) {
	if(record.success) {
		return '<img src=\'./images/success.png\' style=\'cursor: pointer;\'>';
	} else if(record.success == null) {
		return '';
	} else {
		return '<img src=\'./images/failure.png\' style=\'cursor: pointer;\'>';
	}
}

function criteriaTotal_synchronizes(record) {
	return record.criteriaFound + record.criteriaCreated + record.criteriaUpdated;
}
//--- synchronizes --- //

//--- criteriaTaxonomy --- //
function nationalEntityListBoxLocal_criteriaTaxonomy() {
	return nationalEntities;
}

function criterionFound_criteriaTaxonomy(record) {
	if(record.criterionFound) {
		return '<img src=\'./images/success.png\' style=\'cursor: pointer;\'>';
	} else if(record.success == null) {
		return '';
	} else {
		return '<img src=\'./images/failure.png\' style=\'cursor: pointer;\'>';
	}
}

function nationalEntity_criteriaTaxonomy(record) {
	if(nationalEntities[record.nationalEntity] != null) {
		return nationalEntities[record.nationalEntity];
	} else {
		return '';
	}
}
// --- criteriaTaxonomy --- //

//--- criteriaEspd --- //
function criteriaEspd_check(record) {
	var found = false;
	$.each(criteriaEspd, function(idField, field) {
		if(field == record.id) {
			found = true;
		}
	})
	if(found) {
		return '<img onclick=\'criteriaEspd_remove("' + record.id + '", this)\' src=\'./images/checkbox-checked.png\' style=\'cursor: pointer;\'>';
	} else {
		return '<img onclick=\'criteriaEspd_add("' + record.id + '", this)\' src=\'./images/checkbox-unchecked.png\' style=\'cursor: pointer;\'>';
	}
}

function criteriaEspd_add(id, image) {
	$(image).attr('onclick', 'criteriaEspd_remove("' + id + '", this);');
	$(image).attr('src', './images/checkbox-checked.png');
	criteriaEspd.push(id);
	$('#createEspd').show();
}

function criteriaEspd_remove(id, image) {
	$(image).attr('onclick', 'criteriaEspd_add("' + id + '", this);');
	$(image).attr('src', './images/checkbox-unchecked.png');
	criteriaEspd.splice(criteriaEspd.indexOf(id), 1);
	if(criteriaEspd.length == 0) {
		$('#createEspd').hide();
	}
}

function partyShow() {
	$('#partyPopup').show();
}

function createEspd() {
	$('#createEspd').hide();
	var message = '<p>ESPD in creazione ...</p>';
	$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
	var espdRequest = {};
	espdRequest['codiceIdentificativoGara'] = $('#codiceIdentificativoGara').val();
	$.each($('.partyField'), function(idField, field) {
		espdRequest[(field.id)] = field.value;
	})
	espdRequest['criteriaEspd'] = criteriaEspd;
	$.ajax({
		contentType: "application/json",
		data: JSON.stringify(espdRequest),
		type: 'post',
		url: cefespdbuilderUrl + 'createEspd?nationalEntity=it'
	}).done(function(data) {
		serial = new XMLSerializer();
		if (typeof window.navigator.msSaveBlob !== 'undefined') {
			blob = new Blob([serial.serializeToString(data)], {type: 'octet/stream'});
			window.navigator.msSaveBlob(blob, 'espd.xml');
		} else {
			a = document.createElement('a');
			a.style = 'display: none';
			document.body.appendChild(a);
			blob = new Blob([serial.serializeToString(data)], {type: 'octet/stream'});
			url = URL.createObjectURL(blob);
			a.href = url;
			a.download = 'espd.xml';
			a.click();
			URL.revokeObjectURL(url);
		}
		var message = '<p>ESPD creato !</p>';
		$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
		$('#createEspd').show();
	}).fail(function (jqXHR, textStatus) {
		var message = '<p>Errore : ' + jqXHR.status + ' </p>';
		$('#message').empty().append(message).fadeIn(0).delay(2500).fadeOut();
		$('#createEspd').show();
	})
}
//--- criteriaEspd --- //

// --- party ---
function partyClose() {
	$('#partyPopup').hide();
}
// --- party ---