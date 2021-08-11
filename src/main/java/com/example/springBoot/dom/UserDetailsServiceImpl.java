//package com.example.springBoot.dom;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.springBoot.entity.UserEntity;
//import com.example.springBoot.repositories.UserRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // loadUserByUsernameはUserDetailsのメソッド
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserEntity loginUser = userRepository.findUser(email);
//
//        if (loginUser == null) {
//            throw new UsernameNotFoundException("User アカウントが存在しません");
//        }
////       権限の設定（ダミー）
////        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
////        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
////        grantList.add(authority);
//
////      UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
//        UserDetails userDetails = (UserDetails) new User(loginUser.getName(),loginUser.getEmail(), false, false, false, false, null);
//
//        return userDetails;
//    }
//}