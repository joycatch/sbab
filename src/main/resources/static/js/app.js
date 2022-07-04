let presentNumberOfBusLines = 10;

async function getLineNumbersWithMostStops() {
    let url = '/bus-route-service/most-stop-points/' + presentNumberOfBusLines;
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function getStopPoints(lineNumber) {
    let url = '/bus-route-service/stop-points/' + lineNumber;
    try {
        let res = await fetch(url);
        let stopPoints = await res.json();
        renderStopPoints(lineNumber, stopPoints)
    } catch (error) {
        console.log(error);
    }
}

async function renderStopPoints(lineNumber, stopPoints) {
    let html = '<table class="table table-hover">';
    let i = 1;

    stopPoints.forEach(stopPoint => {
        let htmlSegment = `
            <tr>
                <td><span style="color: #ccc;">${i++}.</span> ${stopPoint}</td>
            </tr>`;
        html += htmlSegment;
    });

    html += '</table>';

    let container = document.querySelector('#body-' + lineNumber);
    container.innerHTML = html;
}


async function renderLineNumbersWithMostStops() {
    let entries = await getLineNumbersWithMostStops();
    let html = '';

    entries.forEach(entry => {
        let htmlSegment = `
			<div class="accordion-item" onclick="getStopPoints(${entry.lineNumber})">
				<h2 class="accordion-header" id="flush-headingOne">
					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${entry.lineNumber}" aria-expanded="false" aria-controls="flush-collapse${entry.lineNumber}">
						Bus ${entry.lineNumber} <span style="padding: 0 0 0 10px; color: #ccc;"> (${entry.stopPointCount} round trip stops)</span>
					</button>
				</h2>
				<div id="flush-collapse${entry.lineNumber}" class="accordion-collapse collapse" aria-labelledby="flush-heading${entry.lineNumber}" data-bs-parent="#accordion">
					<div id="body-${entry.lineNumber}" class="accordion-body"></div>
				</div>
			</div>`;
        html += htmlSegment;
    });

    let container = document.querySelector('.accordion');
    container.innerHTML = html;
}

renderLineNumbersWithMostStops();