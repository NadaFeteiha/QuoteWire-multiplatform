//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Nada Feteiha on 4/28/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


import Foundation
import shared

extension HomeScreen {
    @MainActor class HomeViewModel: ObservableObject {
        

        @Published private(set) var images:[QuoteImage] = []
        @Published private(set) var isLoading: Bool = false

        private var currentPage = 1
        private(set) var loadFinished: Bool = false

        func loadImages() async {
            if isLoading {
                return
            }

            do {
                self.images = try await GetImagesUseCase.init().invoke()
                print(self.images)
                isLoading = false

            } catch {
                isLoading = false
                loadFinished = true

                print(error.localizedDescription)
            }
        }
    }
}
