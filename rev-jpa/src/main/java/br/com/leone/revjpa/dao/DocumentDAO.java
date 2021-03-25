package br.com.leone.revjpa.dao;

import br.com.leone.revjpa.domain.Document;

public class DocumentDAO extends GenericDAO<Document> {

    public DocumentDAO() {
        super(Document.class);
    }
}
