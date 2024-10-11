package com.binnosoc.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<SchoolEntity> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<SchoolEntity> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public SchoolEntity createSchool(SchoolEntity school) {
        return schoolRepository.save(school);
    }

    public SchoolEntity updateSchool(Long id, SchoolEntity updatedSchool) {
        Optional<SchoolEntity> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isPresent()) {
            SchoolEntity school = schoolOpt.get();
            school.setName(updatedSchool.getName());
            school.setAddress(updatedSchool.getAddress());
            return schoolRepository.save(school);
        }
        return null;
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}

