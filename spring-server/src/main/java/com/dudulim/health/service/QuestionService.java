package com.dudulim.health.service;

import com.dudulim.health.domain.Member;
import com.dudulim.health.domain.Question;
import com.dudulim.health.dto.QuestionRequest;
import com.dudulim.health.dto.QuestionResponse;
import com.dudulim.health.repository.MemberRepository;
import com.dudulim.health.repository.QuestionRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public QuestionService(QuestionRepository questionRepository, MemberRepository memberRepository) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
    }

    public List<QuestionResponse> findAll() {
        return questionRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(QuestionResponse::new)
                .toList();
    }

    public QuestionResponse findById(Long id) {
        return new QuestionResponse(getQuestion(id));
    }

    public QuestionRequest findUpdateForm(Long id, String username, boolean admin) {
        Question question = getQuestion(id);
        validateEditable(question, username, admin);

        QuestionRequest request = new QuestionRequest();
        request.setTitle(question.getTitle());
        request.setContent(question.getContent());
        return request;
    }

    @Transactional
    public Long create(QuestionRequest request, String username) {
        Member writer = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("로그인 사용자를 찾을 수 없습니다."));

        Question question = new Question(request.getTitle(), request.getContent(), writer);
        return questionRepository.save(question).getId();
    }

    @Transactional
    public void update(Long id, QuestionRequest request, String username, boolean admin) {
        Question question = getQuestion(id);
        validateEditable(question, username, admin);
        question.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void delete(Long id, String username, boolean admin) {
        Question question = getQuestion(id);
        validateEditable(question, username, admin);
        questionRepository.delete(question);
    }

    private Question getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    private void validateEditable(Question question, String username, boolean admin) {
        if (question.getWriter() == null) {
            if (admin) {
                return;
            }
            throw new AccessDeniedException("작성자가 없는 게시글은 관리자만 수정 또는 삭제할 수 있습니다.");
        }

        if (!question.getWriter().getUsername().equals(username)) {
            throw new AccessDeniedException("작성자 본인만 수정 또는 삭제할 수 있습니다.");
        }
    }
}
