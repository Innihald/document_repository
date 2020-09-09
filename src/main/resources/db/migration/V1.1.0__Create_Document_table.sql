CREATE TABLE "public"."document" (
                                     "id" bigint NOT NULL,
                                     "description" character varying(255),
                                     "title" character varying(255),
                                     "file_id" bigint,
                                     "ocr_id" bigint,
                                     CONSTRAINT "document_pkey" PRIMARY KEY ("id"),
                                     CONSTRAINT "document_file_releation" FOREIGN KEY (file_id) REFERENCES physical_file(id) NOT DEFERRABLE,
                                     CONSTRAINT "document_ocr_releation" FOREIGN KEY (ocr_id) REFERENCES ocr_data(id) NOT DEFERRABLE
) WITH (oids = false);