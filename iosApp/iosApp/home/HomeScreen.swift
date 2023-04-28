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
    
    var body: some View {

        NavigationStack{
            
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.images, id: \.id){quoteImage in
                        
                        NavigationLink(value: quoteImage){
                            
                            AsyncImage(url: URL(string: quoteImage.imageURL)){image in
                                        image.resizable()
                                    }placeholder: {
                                        Color.gray
                                    }
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                    
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
//                .navigationDestination(for: Movie.self){movie in
//                    DetailScreen(movie: movie)
//                }
                
            }
            .navigationTitle("Quotes ... ")
            
        }
        .task {
            await viewModel.loadImages()
        }
    }
}
