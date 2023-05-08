//
//  HomeScreen.swift
//  iosApp
//
//  Created by Nada Feteiha on 4/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI
import shared

struct HomeScreen: View {
    
    @StateObject var viewModel = HomeViewModel()
    
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)

    init() {
        let font = UIFontMetrics(forTextStyle: .largeTitle).scaledFont(for: UIFont(name: "DancingScript-Bold", size: 30)!)
        UINavigationBar.appearance().tintColor = UIColor(rgb: 0xFF121213)
        UINavigationBar.appearance().largeTitleTextAttributes = [ .foregroundColor: UIColor(rgb: 0xFF121213), .font: font    ]
    }
    
    
    var body: some View {
        
        NavigationView{
            
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.images, id: \.id){quoteImage in
                        NavigationLink(destination: DetailScreen(quoteImage: quoteImage )) {
                            AsyncImage(url: URL(string: quoteImage.imageURL)){image in
                                image.resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(height: 200)
                                    .cornerRadius(20, corners: [.topLeft, .bottomRight])

                            }placeholder: {
                                Color.gray
                            }
                        }
                        
                    }
                    
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
            }.background(Color(UIColor(rgb: 0xFFFCF8F5)))
            .navigationTitle("Quote Wire")
            .navigationBarTitleDisplayMode(.inline)
            .font(Font.custom("DancingScript-Bold", size: 24))
        } .accentColor(Color(UIColor(rgb: 0xFFFCF8F5)))
        .task {
            await viewModel.loadImages()
        }
    }
}

extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape( RoundedCorner(radius: radius, corners: corners) )
    }
}

struct RoundedCorner: Shape {

    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}
