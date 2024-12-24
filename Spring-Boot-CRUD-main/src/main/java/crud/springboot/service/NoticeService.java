package crud.springboot.service;

import crud.springboot.model.Notice;
import java.util.List;

public interface NoticeService {
    List<Notice> getAllNotices();
    void saveNotice(Notice notice);
}
