import {LitElement, html} from 'lit-element';

class DocumentList extends LitElement {
    render() {
        return html`
            <div>
                <h1>Document List</h1>
                <vaadin-button>Button</vaadin-button>
            </div>
        `;
    }
}

customElements.define("document-list", DocumentList);