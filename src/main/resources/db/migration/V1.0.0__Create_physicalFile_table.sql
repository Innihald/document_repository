CREATE TABLE "public"."physical_file" (
                                          "id" bigint NOT NULL,
                                          "filename" character varying(255),
                                          "path" character varying(255),
                                          CONSTRAINT "physical_file_pkey" PRIMARY KEY ("id")
) WITH (oids = false);