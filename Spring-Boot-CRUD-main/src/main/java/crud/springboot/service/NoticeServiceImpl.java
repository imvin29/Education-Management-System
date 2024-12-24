package crud.springboot.service;

import crud.springboot.model.Notice;
import crud.springboot.repository.NoticeRepository;
import crud.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    @Override
    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }
}
