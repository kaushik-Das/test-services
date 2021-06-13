package com.dadamohit.mediaservice.services;

import com.dadamohit.servicecommon.models.Media;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import java.io.IOException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

  @Autowired
  private GridFsTemplate gridFsTemplate;

  @Autowired
  private GridFsOperations gridFsOperations;

  public String addMedia(String name, MultipartFile file) throws IOException {
    DBObject metadata = new BasicDBObject();
    metadata.put("name", name);
    metadata.put("type", file.getContentType());
    final ObjectId id = gridFsTemplate.store(file.getInputStream(),
        file.getName(), file.getContentType(), metadata);
    return id.toString();
  }

  public Media getMedia(String id) throws IOException {
    GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    final String fileName = file.getMetadata().get("name").toString();
    final String fileType = file.getMetadata().get("type").toString();
    final byte[] content = gridFsOperations.getResource(file).getContent().readAllBytes();
    Media media = new Media(fileName, fileType, content);
    return media;
  }

  public void deleteMedia(String id) {
    gridFsTemplate.delete(new Query(Criteria.where("id").is(id)));
  }
}
