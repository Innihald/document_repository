CREATE TABLE "public"."ocr_data" (
                                     "id" bigint NOT NULL,
                                     "text" text,
                                     CONSTRAINT "ocr_data_pkey" PRIMARY KEY ("id")
) WITH (oids = false);